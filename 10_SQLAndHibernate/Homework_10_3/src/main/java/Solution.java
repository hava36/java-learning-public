import dao.*;
import entities.*;
import entities.keys.LinkedPurchaseKey;
import service.ConnectionUtil;

import java.util.List;

public class Solution {

    public static void main(String[] args) {

        DAO purchaseDAO = new PurchaseDAO();
        List<Purchase> purchaseList = purchaseDAO.readAll();

        DAO linkedPurchaseDAO = new LinkedPurchaseDAO();

        for (Purchase purchase: purchaseList
             ) {

            LinkedPurchase linkedPurchase = new LinkedPurchase();
            linkedPurchase.setId(new LinkedPurchaseKey(purchase.getStudentId(),
                    purchase.getCourseId()));

            linkedPurchaseDAO.update(linkedPurchase);

        }

        List<LinkedPurchase> linkedPurchaseList = linkedPurchaseDAO.readAll();

        ConnectionUtil.getSessionFactory().close();

    }

}
