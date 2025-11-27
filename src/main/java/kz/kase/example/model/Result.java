package kz.kase.example.model;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * <p>Результат выполнения запроса.</p>
 * <p><b>Created:</b> 24.11.2025 17:08:16</p>
 * @author victor
 */
@Schema(type = SchemaType.OBJECT, title = "Результат", description = "Результат выполнения запроса.")
public class Result {
   @Schema(type = SchemaType.BOOLEAN, description = "Статус выполнения запроса (true - успешно, false - ошибка)")
   public Boolean ok;
   @Schema(type = SchemaType.STRING, description = "Описание ошибки (в случае если ok=false)")
   public String error_message;

   //==============================================================
   // Конструкторы.
   //==============================================================

   public Result() {
      ok = true;
   }

   public Result(String errorMessage) {
      this.error_message = errorMessage;
      ok = false;
   }
}
