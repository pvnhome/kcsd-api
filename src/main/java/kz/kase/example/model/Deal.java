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
   public static final DateTimeFormatter ANSI = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

   @Schema(type = SchemaType.INTEGER, format = "int64", description = "Уникальный код сделки в информационной системе ЦД.")
   public Long id;
   @Schema(type = SchemaType.STRING, format = "date-time", description = "Дата и время сделки в формате \"yyyy-MM-dd HH:mm:ss\"", examples = {"2025-11-25 22:55:25"})
   @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
   public LocalDateTime dealTime;
   @Schema(type = SchemaType.STRING, description = "Первичный или вторичный рынок", enumeration = {"PRIMARY", "SECONDARY"}, minLength = 7, maxLength = 9, examples = {"PRIMARY", "SECONDARY"})
   public String market;
   @Schema(type = SchemaType.STRING, description = "Вид сделки")
   public String dealType;
   /*
   @Schema(type = SchemaType.STRING, description = "Тип ценной бумаги")
   public String secType;
   */
   @Schema(type = SchemaType.STRING, description = "Код ценной бумаги")
   public String code;
   @Schema(type = SchemaType.STRING, description = "Код ISIN")
   public String isin;
   /*
   @Schema(type = SchemaType.STRING, description = "Наименование эмитента")
   public String issuer;
   */
   @Schema(type = SchemaType.INTEGER, format = "int64", description = "Количество финансовых инструментов (штук)")
   public Long quantity;
   @Schema(type = SchemaType.NUMBER, format = "double", description = "Цена в валюте сделки")
   public BigDecimal price;
   @Schema(type = SchemaType.STRING, description = "Валюта сделки")
   public String currency;
   @Schema(type = SchemaType.NUMBER, format = "double", description = "Объём сделки, в тенге")
   public BigDecimal amount;
   /*
   @Schema(type = SchemaType.STRING, description = "В номинальном держании, у регистратора или другое", enumeration = {"NOM", "REG", "OTHER"}, minLength = 3, maxLength = 5, examples = {"NOM", "REG"})
   public String registration;
   */
   /*
   @Schema(type = SchemaType.INTEGER, format = "int32", description = "Код агента в информационной системе ЦД")
   public Integer agentId;
   */
   @Schema(type = SchemaType.BOOLEAN, description = "Покупатель резидент (true - резидент, false - не резидент)")
   public Boolean agentResidency;
   @Schema(type = SchemaType.BOOLEAN, description = "Покупатель юридическое лицо (true - юридическое, false - физическое)")
   public Boolean agentType;
   /*
   @Schema(type = SchemaType.INTEGER, format = "int32", description = "Код контрагента в информационной системе ЦД")
   public Integer contragentId;
   */
   @Schema(type = SchemaType.BOOLEAN, description = "Продавец резидент (true - резидент, false - не резидент)")
   public Boolean contragentResidency;
   @Schema(type = SchemaType.BOOLEAN, description = "Продавец юридическое лицо (true - юридическое, false - физическое)")
   public Boolean contragentType;

   //==============================================================
   // Конструкторы.
   //==============================================================

   public Deal() {
   }

   /*
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
   */

   public Deal(Long id, String time, String market, String dealType, String code, String isin, Long quantity, String price, String currency, String amount, Boolean agentResidency, Boolean agentType, Boolean contragentResidency, Boolean contragentType) {
      this.id = id;
      this.dealTime = LocalDateTime.parse(time, ANSI);
      this.market = market;
      this.dealType = dealType;
      this.code = code;
      this.isin = isin;
      this.quantity = quantity;
      this.price = new BigDecimal(price);
      this.currency = currency;
      this.amount = new BigDecimal(amount);
      this.agentResidency = agentResidency;
      this.agentType = agentType;
      this.contragentResidency = contragentResidency;
      this.contragentType = contragentType;
   }
}
