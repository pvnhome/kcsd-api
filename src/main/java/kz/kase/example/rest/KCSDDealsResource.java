package kz.kase.example.rest;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import kz.kase.example.model.Deal;
import kz.kase.example.model.Result;
import kz.kase.example.services.DbService;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/kcsd")
public class KCSDDealsResource {
   @Inject
   private DbService dbService;

   //@formatter:off
   @POST
   @Path("/deals")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   @Operation(
      summary = "Загрузка списка внебиржевых сделок",
      description = "Метод предназначен для передачи списка внебиржевых сделок.",
      operationId = "uploadKcsdDeals"
   )
   @Tag(name = "KCSD_DEALS", description = "Методы REST API для работы с внебиржевыми сделками.")
   @APIResponses( value = {
      @APIResponse(responseCode = "200", description = "Успешное выполнение операции", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Result.class))),
      @APIResponse(responseCode = "400", description = "Неверный формат параметров", content = @Content(mediaType = MediaType.TEXT_HTML)),
      @APIResponse(responseCode = "404", description = "Запрашиваемый ресурс не найден на сервере", content = @Content(mediaType = MediaType.TEXT_HTML)),
      @APIResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Result.class)))
   })
   public Response uploadKcsdDeals(
      //@QueryParam("deals")
      @Parameter(
         in = ParameterIn.DEFAULT,
         name = "deals",
         description = ".",
          schema = @Schema(
             type = SchemaType.OBJECT
          )
      )
      List<Deal> deals
   ) {
   //@formatter:on
      Log.infof("uploadKcsdDeals: cnt=%d", deals == null ? 0 : deals.size());
      try {
         if (deals != null && !deals.isEmpty()) {
            int add = 0, upd = 0, err = 0;

            for (Deal d : deals) {
               if (Log.isTraceEnabled()) {
                  Log.trace("   " + d.toString());
               }

               if (d.id == null) {
                  Log.warn("   Deal without id");
                  err++;
               } else if (d.deal_time == null) {
                  Log.warnf("   No deal_time for deal with id=%d", d.id);
                  err++;
               } else {
                  switch (dbService.saveDeal(d)) {
                     case ADD:
                        add++;
                        break;
                     case UPDATE:
                        upd++;
                        break;
                  }
               }
            }


            if (err > 0) {
               dbService.saveSession(add, upd, err, "Количество ошибочных сделок: " + err);
               return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new Result("Количество ошибочных сделок: " + err)).build();
            } else {
               dbService.saveSession(add, upd, err);
               return Response.ok(new Result()).build();
            }
         } else {
            Log.warnf("uploadKcsdDeals: %s", deals == null ? "no deals" : "empty deals list");
            dbService.saveSessionError("В запросе отсутствуют сделки");
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new Result("В запросе отсутствуют сделки")).build();
         }
      } catch (Exception e) {
         Log.error("Exception in uploadKcsdDeals", e);
         dbService.saveSessionError(e);
         return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new Result(e.getMessage())).build();
      }
   }
}
