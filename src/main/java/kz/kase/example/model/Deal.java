package kz.kase.example.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.json.bind.annotation.JsonbDateFormat;

/**
 * <p>Сделка.</p>
 * <p><b>Created:</b> 06.05.2025 14:30:56</p>
 * @author victor
 */
@Schema(type = SchemaType.OBJECT, title = "Сделка", description = "Запись внебиржевой сделки.")
public class Deal {
   private static final DateTimeFormatter ANSI = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

   @Schema(type = SchemaType.INTEGER, format = "int64", description = "Уникальный код сделки в информационной системе ЦД.")
   public Long id;
   @Schema(type = SchemaType.STRING, format = "date", description = "Дата и время сделки в формате \"yyyy-MM-ddTHH:mm:ss\"")
   @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
   public LocalDateTime dealTime;
   @Schema(type = SchemaType.STRING, description = "Первичный или вторичный рынок", enumeration = {"PRIMARY", "SECONDARY"}, minLength = 7, maxLength = 9, examples = {"PRIMARY", "SECONDARY"})
   public String market;
   @Schema(type = SchemaType.STRING, description = "Вид сделки")
   public String dealType;
   @Schema(type = SchemaType.STRING, description = "Тип ценной бумаги")
   public String secType;
   @Schema(type = SchemaType.STRING, description = "Код ценной бумаги")
   public String code;
   @Schema(type = SchemaType.STRING, description = "Код ISIN")
   public String isin;
   @Schema(type = SchemaType.STRING, description = "Наименование эмитента")
   public String issuer;
   @Schema(type = SchemaType.INTEGER, format = "int64", description = "Количество финансовых инструментов в штуках")
   public Long quantity;
   @Schema(type = SchemaType.NUMBER, format = "double", description = "Цена за один финансовый инструмент в валюте сделки")
   public BigDecimal price;
   @Schema(type = SchemaType.STRING, description = "Валюта сделки")
   public String currency;
   @Schema(type = SchemaType.NUMBER, format = "double", description = "Объем сделки в тенге")
   public BigDecimal volKzt;
   @Schema(type = SchemaType.STRING, description = "В номинальном держании, у регистратора или другое", enumeration = {"NOM", "REG", "OTHER"}, minLength = 3, maxLength = 5, examples = {"NOM", "REG"})
   public String registration;
   @Schema(type = SchemaType.INTEGER, format = "int32", description = "Код агента в информационной системе ЦД")
   public Integer agentId;
   @Schema(type = SchemaType.BOOLEAN, description = "Является ли агент резидентом")
   public Boolean agentResident;
   @Schema(type = SchemaType.BOOLEAN, description = "Является ли агент физическим лицом")
   public Boolean agentIndividual;
   @Schema(type = SchemaType.INTEGER, format = "int32", description = "Код контрагента в информационной системе ЦД")
   public Integer contragentId;
   @Schema(type = SchemaType.BOOLEAN, description = "Является ли контрагент резидентом")
   public Boolean contragentResident;
   @Schema(type = SchemaType.BOOLEAN, description = "Является ли контрагент физическим лицом")
   public Boolean contragentIndividual;

   public Deal() {
   }

   public Deal(Long id, String time, String market, String dealType, String secType, String code, String isin, String issuer, Long quantity, String price, String currency, String volKzt, String registration, Integer agentId, Boolean agentResident, Boolean agentIndividual, Integer contragentId, Boolean contragentResident, Boolean contragentIndividual) {
      this.id = id;
      this.dealTime = LocalDateTime.parse(time, ANSI);
      this.market = market;
      this.dealType = dealType;
      this.secType = secType;
      this.code = code;
      this.isin = isin;
      this.issuer = issuer;
      this.quantity = quantity;
      this.price = new BigDecimal(price);
      this.currency = currency;
      this.volKzt = new BigDecimal(volKzt);
      this.registration = registration;
      this.agentId = agentId;
      this.agentResident = agentResident;
      this.agentIndividual = agentIndividual;
      this.contragentId = contragentId;
      this.contragentResident = contragentResident;
      this.contragentIndividual = contragentIndividual;
   }
}
