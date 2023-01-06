package com.vouchit.backend.service.analysis;

import com.vouchit.backend.model.entity.Category;
import com.vouchit.backend.model.entity.Coupon;
import com.vouchit.backend.model.entity.Purchase;
import com.vouchit.backend.repository.CategoryRepository;
import com.vouchit.backend.repository.CouponRepository;
import com.vouchit.backend.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;


@Service
public class AnalysisService {


    private final CouponRepository couponRepository;

    private final PurchaseRepository purchaseRepository;

    private final CategoryRepository categoryRepository;


    public AnalysisService(
            CouponRepository couponRepository,
            PurchaseRepository purchaseRepository,
            CategoryRepository categoryRepository) {
        this.couponRepository = couponRepository;
        this.purchaseRepository = purchaseRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * This function returns the total sales of a company
     * and coupon sales per month of the current year
     *
     * @param companyId
     * @return
     */
    public HashMap<String, HashMap<String, Long>> analysis(Long companyId) {
        // Get all the coupons of the company
        var couponsOfCompany = couponRepository.getAllByCompanyId(companyId);
        List<Long> couponIds = couponsOfCompany.stream().map(Coupon::getId).toList();

        // get all purchases that contain the coupons of the company
        var purchasesContainingCoupons = purchaseRepository.getAllByCoupons(couponIds);//.stream().filter(purchase -> purchase.getCoupons().size() > 0).toList();

        // get all total purchases containing coupons of the company
        HashMap<String, Long> totalSales = new HashMap<>();
        long totalCoupons  = purchasesContainingCoupons.size();
        totalSales.put("0", totalCoupons);

        // get all coupon sales per month of the current year
        HashMap<String, HashMap<String, Long>> analysis = getSalesPerMonth(purchasesContainingCoupons);
        analysis.put("totalCouponsSold", totalSales);
        analysis.put("salesPerCategory", getSalesPerCategory(purchasesContainingCoupons, couponsOfCompany));

//        HashMap<String, HashMap<String,Long>> salesPerCategory = getSalesPerCategory(purchasesContainingCoupons);
        return analysis;
    }

    private HashMap<String, Long> getSalesPerCategory(List<Purchase> purchasesContainingCoupons, Set<Coupon> couponsOfCompany) {
        HashMap<String, Long> salesPerCategory = new HashMap<>();

        // works but not efficient
//        couponsOfCompany.stream()
//                        .map(Coupon::getCategory)
//                        .forEach(category -> {
//                            long count = purchasesContainingCoupons.stream()
//                                                                  .filter(purchase -> purchase.getCoupons().stream()
//                                                                                              .anyMatch(coupon -> coupon.getCategory().equals(category)))
//                                                                  .count();
//                            salesPerCategory.put(category.getName(), count);
//                        });

        // TODO: make it more efficient

        // this query check for each category if there is a coupon of that category in the purchase
        couponsPerCategory(couponsOfCompany, salesPerCategory);
//        categories.forEach(category -> {
//            long count = purchasesContainingCoupons.stream()
//                                                  .filter(purchase -> purchase.getCoupons().stream()
//                                                                              .anyMatch(coupon -> coupon.getCategory().equals(category)))
//                                                  .count();
//            salesPerCategory.put(category.getName(), count);
//        });


        return salesPerCategory;
    }

    private void couponsPerCategory(Set<Coupon> couponsOfCompany, HashMap<String, Long> salesPerCategory) {
        List<Category> categories = categoryRepository.findAll();
        categories.forEach(category -> {
            long count = couponsOfCompany.stream()
                                         .filter(coupon -> coupon.getCategory().equals(category))
                                         .count();
            salesPerCategory.put(category.getName(), count);
        });
    }


    private static HashMap<String, HashMap<String, Long>> getSalesPerMonth( List<Purchase> purchasesContainingCoupons) {
        HashMap<String, HashMap<String,Long>> analysis = new HashMap<>();
        HashMap<String, Long> salesPerMonthMap = new HashMap<>();
        salesPerMonthMap.put("1", 0L);
        salesPerMonthMap.put("2", 0L);
        salesPerMonthMap.put("3", 0L);
        salesPerMonthMap.put("4", 0L);
        salesPerMonthMap.put("5", 0L);
        salesPerMonthMap.put("6", 0L);
        salesPerMonthMap.put("7", 0L);
        salesPerMonthMap.put("8", 0L);
        salesPerMonthMap.put("9", 0L);
        salesPerMonthMap.put("10", 0L);
        salesPerMonthMap.put("11", 0L);
        salesPerMonthMap.put("12", 0L);
        analysis.put("salesPerMonth", salesPerMonthMap);

        purchasesContainingCoupons.forEach(purchase -> {
            final var monthValue = purchase.getDate().getMonthValue();
            final var sales = analysis.get("salesPerMonth").get(String.valueOf(monthValue));
            analysis.get("salesPerMonth").put(String.valueOf(monthValue), sales + 1);
        });
        return analysis;
    }
}
