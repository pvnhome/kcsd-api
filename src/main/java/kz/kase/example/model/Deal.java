package kz.kase.example.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

/**
 * <p>Сделка.</p>
 * <p><b>Created:</b> 06.05.2025 14:30:56</p>
 * @author victor
 */
@Entity
@Table(name = "deals", indexes = {@Index(name = "ind_deals_deal_time", columnList = "deal_time")})
@Schema(type = SchemaType.OBJECT, title = "Сделка", description = "Запись внебиржевой сделки.")
public class Deal extends PanacheEntityBase {
   public static final DateTimeFormatter ANSI = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

   @Id
   @Column(comment = "Уникальный код сделки в информационной системе ЦД")
   @Schema(type = SchemaType.INTEGER, format = "int64", description = "Уникальный код сделки в информационной системе ЦД")
   public Long id;
   @Column(comment = "Уникальный референс сообщения")
   @Schema(type = SchemaType.INTEGER, format = "int32", description = "Уникальный референс сообщения, системный для системы источника (ЦДЦБ), по нему определяется дата-время-тип запроса и отправленный данные")
   public Integer ref;
   @Column(nullable = false, comment = "Дата и время сделки")
   @Schema(type = SchemaType.STRING, format = "date-time", description = "Дата и время сделки в формате \"yyyy-MM-dd HH:mm:ss\"", examples = {"2025-11-25 22:55:25"})
   @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
   public LocalDateTime deal_time;
   @Schema(type = SchemaType.STRING, description = "Первичный или вторичный рынок", enumeration = {"PRIMARY", "SECONDARY"}, minLength = 7, maxLength = 9, examples = {"PRIMARY", "SECONDARY"})
   public String market;
   @Schema(type = SchemaType.STRING, description = "Вид сделки")
   public String deal_type;
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
   @Column(precision = 38, scale = 4, comment = "Цена в валюте сделки")
   @Schema(type = SchemaType.NUMBER, format = "double", description = "Цена в валюте сделки")
   public BigDecimal price;
   @Schema(type = SchemaType.STRING, description = "Валюта сделки")
   public String currency;
   @Column(precision = 38, scale = 2, comment = "Объём сделки, в тенге")
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
   public Boolean agent_residency;
   @Schema(type = SchemaType.BOOLEAN, description = "Покупатель юридическое лицо (true - юридическое, false - физическое)")
   public Boolean agent_type;
   /*
   @Schema(type = SchemaType.INTEGER, format = "int32", description = "Код контрагента в информационной системе ЦД")
   public Integer contragentId;
   */
   @Schema(type = SchemaType.BOOLEAN, description = "Продавец резидент (true - резидент, false - не резидент)")
   public Boolean contragent_residency;
   @Schema(type = SchemaType.BOOLEAN, description = "Продавец юридическое лицо (true - юридическое, false - физическое)")
   public Boolean contragent_type;

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
      this.deal_time = LocalDateTime.parse(time, ANSI);
      this.market = market;
      this.deal_type = dealType;
      this.code = code;
      this.isin = isin;
      this.quantity = quantity;
      this.price = new BigDecimal(price);
      this.currency = currency;
      this.amount = new BigDecimal(amount);
      this.agent_residency = agentResidency;
      this.agent_type = agentType;
      this.contragent_residency = contragentResidency;
      this.contragent_type = contragentType;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Deal [");
      if (id != null) {
         builder.append("id=");
         builder.append(id);
         builder.append(", ");
      }
      if (deal_time != null) {
         builder.append("deal_time=");
         builder.append(deal_time.format(Deal.ANSI));
         builder.append(", ");
      }
      if (market != null) {
         builder.append("market=");
         builder.append(market);
         builder.append(", ");
      }
      if (deal_type != null) {
         builder.append("deal_type=");
         builder.append(deal_type);
         builder.append(", ");
      }
      if (code != null) {
         builder.append("code=");
         builder.append(code);
         builder.append(", ");
      }
      if (isin != null) {
         builder.append("isin=");
         builder.append(isin);
         builder.append(", ");
      }
      if (quantity != null) {
         builder.append("quantity=");
         builder.append(quantity);
         builder.append(", ");
      }
      if (price != null) {
         builder.append("price=");
         builder.append(price);
         builder.append(", ");
      }
      if (currency != null) {
         builder.append("currency=");
         builder.append(currency);
         builder.append(", ");
      }
      if (amount != null) {
         builder.append("amount=");
         builder.append(amount);
         builder.append(", ");
      }
      if (agent_residency != null) {
         builder.append("agent_residency=");
         builder.append(agent_residency);
         builder.append(", ");
      }
      if (agent_type != null) {
         builder.append("agent_type=");
         builder.append(agent_type);
         builder.append(", ");
      }
      if (contragent_residency != null) {
         builder.append("contragent_residency=");
         builder.append(contragent_residency);
         builder.append(", ");
      }
      if (contragent_type != null) {
         builder.append("contragent_type=");
         builder.append(contragent_type);
      }
      builder.append("]");
      return builder.toString();
   }

}
