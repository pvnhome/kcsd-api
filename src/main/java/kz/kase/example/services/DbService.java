package kz.kase.example.services;

import java.time.LocalDateTime;

import kz.kase.example.model.Deal;
import kz.kase.example.model.SessionStatus;
import kz.kase.example.model.Sessions;

import jakarta.enterprise.context.Dependent;
import jakarta.transaction.Transactional;

/**
 * <p>Методы для работы с БД в рамках транзакций.</p>
 * <p><b>Created:</b> 26.11.2025 13:19:57</p>
 * @author victor
 */
@Dependent
public class DbService {
   public enum Action {
      ADD,
      UPDATE
   }

   //==============================================================
   // Методы для работы со сделками.
   //==============================================================

   @Transactional
   public Action saveDeal(Deal deal) {
      Deal dealInDb = Deal.findById(deal.id);
      if (dealInDb == null) {
         deal.persist();
         return Action.ADD;
      } else {
         dealInDb.ref = deal.ref;
         dealInDb.deal_time = deal.deal_time;
         dealInDb.market = deal.market;
         dealInDb.deal_type = deal.deal_type;
         dealInDb.code = deal.code;
         dealInDb.isin = deal.isin;
         dealInDb.quantity = deal.quantity;
         dealInDb.price = deal.price;
         dealInDb.currency = deal.currency;
         dealInDb.amount = deal.amount;
         dealInDb.agent_residency = deal.agent_residency;
         dealInDb.agent_type = deal.agent_type;
         dealInDb.contragent_residency = deal.contragent_residency;
         dealInDb.contragent_type = deal.contragent_type;
         dealInDb.persist();
         return Action.UPDATE;
      }
   }

   //==============================================================
   // Методы для работы с сессиями.
   //==============================================================

   @Transactional
   public void saveSession(Integer addCnt, Integer updateCnt, Integer errorCnt) {
      saveSession(addCnt, updateCnt, errorCnt, null);
   }

   @Transactional
   public void saveSessionError(String errorMessage) {
      saveSession(0, 0, 0, errorMessage);
   }

   @Transactional
   public void saveSessionError(Throwable e) {
      saveSession(0, 0, 0, e.getMessage());
   }

   @Transactional
   public void saveSession(Integer addCnt, Integer updateCnt, Integer errorCnt, String errorMessage) {
      Sessions sess = new Sessions();

      sess.endTime = LocalDateTime.now();
      sess.addCnt = addCnt;
      sess.updateCnt = updateCnt;
      sess.errorCnt = errorCnt;

      if (errorMessage != null) {
         sess.errorMessage = errorMessage;
         sess.status = SessionStatus.ERROR;
      } else {
         sess.status = SessionStatus.OK;
      }

      sess.persist();
   }
}
