package com.Business;

import com.DAL.DAO.*;
import com.DAL.DTO.*;

import java.util.*;


;

public class Supplier {
    private String name;
    private int supplierId;
    private SupplierCard sCard;
    //private DeliveryTerms deliveryTerms;

    private boolean canDeliver;
    private boolean workOnSpecificDays;
    private boolean[] daysCanSupply;

    // each product of the supplier by his serial number
    private HashMap<Integer,Integer > productsByCatalogNum;

    // hashmap from product to his original price before discounts.
    private HashMap<Integer, Double> productsPrice;

    // the key is Product , the value is array of the product's discounts ,
    private HashMap<Integer, List<DiscountSuppliers>> productsDiscount;

    // the key is product , the value is the maximum quantity of this product
    private HashMap<Integer, Integer> productsQuantities;

    private List<DiscountSuppliers> totalDiscountSuppliers;

    String address;

    private List <Order> orders;



    SupplierMapper supplierMapper;
    SupplierCardMapper supplierCardMapper;
    DiscountSupplierMapper discountSupplierMapper;
    DiscountPerSupplierMapper discountPerSupplierMapper;
    OrderMapper orderMapper;
    OrderConstMapper orderConstMapper;
    ProductQuantitiesAndPriceMapper productQuantitiesAndPriceMapper;
    ProductSupplierDiscountMapper productSupplierDiscountMapper;
    SupplierProductMapper supplierProductMapper;






    public Supplier(int supplierId, SupplierCard sc, boolean canDeliver, String daysCanSupply, String name, String address) {
        this.name = name;
        this.supplierId = supplierId;
        this.sCard = sc;
        this.canDeliver = canDeliver;
        ////////////

        String daysToString = daysToString();
        this.address = address;
        this.daysCanSupply = daysToArray(daysCanSupply);
        this.canDeliver = canDeliver;
        this.workOnSpecificDays = daysToString.length() > 0;
        ///////////
        productsByCatalogNum = new HashMap<>();
        productsPrice = new HashMap<>();
        productsDiscount = new HashMap<>();
        productsQuantities = new HashMap<>();
        totalDiscountSuppliers = new ArrayList<>();
        //////////////
        supplierMapper = new SupplierMapper();
        supplierCardMapper = new SupplierCardMapper();
        discountPerSupplierMapper = new DiscountPerSupplierMapper();
        orderMapper = new OrderMapper();
        orderConstMapper = new OrderConstMapper();
        discountPerSupplierMapper = new DiscountPerSupplierMapper();
        productQuantitiesAndPriceMapper = new ProductQuantitiesAndPriceMapper();
        productSupplierDiscountMapper = new ProductSupplierDiscountMapper();
        supplierProductMapper = new SupplierProductMapper();
        ///////////////
    }

    public Supplier (SupplierDTO supplierDTO) {
        this.supplierId = supplierDTO.getId();
        this.name = supplierDTO.getName();
        this.daysCanSupply = daysToArray(supplierDTO.getDays());
        this.address = supplierDTO.getAddress();
        this.canDeliver = supplierDTO.getCanDeliver();
        sCard = new SupplierCard(supplierCardMapper.selectSupplierCard(supplierId));
        /////
        productsByCatalogNum = new HashMap<>();
        productsPrice = new HashMap<>();
        productsDiscount = new HashMap<>();
        productsQuantities = new HashMap<>();
        totalDiscountSuppliers = new ArrayList<>();
        //////////////
        supplierMapper = new SupplierMapper();
        supplierCardMapper = new SupplierCardMapper();
        discountPerSupplierMapper = new DiscountPerSupplierMapper();
        orderMapper = new OrderMapper();
        orderConstMapper = new OrderConstMapper();
        discountPerSupplierMapper = new DiscountPerSupplierMapper();
        productQuantitiesAndPriceMapper = new ProductQuantitiesAndPriceMapper();
        productSupplierDiscountMapper = new ProductSupplierDiscountMapper();
        supplierProductMapper = new SupplierProductMapper();


        int canDeliverInt =0;
        if (canDeliver){
            canDeliverInt = 1;
        }
        String daysToString= daysToString();
        supplierMapper.addSupplier(supplierId,name,canDeliverInt,daysToString, address);


        loadSupplierFromDAL();
    }








    private void loadSupplierFromDAL(){

        List <ProductQuantitiesAndPriceDTO>  productQuantitiesAndPriceDTOSList = productQuantitiesAndPriceMapper.getProductQuantitiesAndPriceDTO(supplierId);
        supplierProductMapper.getSpecificProducts(productQuantitiesAndPriceDTOSList);
        for (ProductQuantitiesAndPriceDTO productQuantitiesAndPriceDTO: productQuantitiesAndPriceDTOSList){
            productsPrice.put(productQuantitiesAndPriceDTO.getProductId(),productQuantitiesAndPriceDTO.getPrice());
            productsQuantities.put(productQuantitiesAndPriceDTO.getProductId(),productQuantitiesAndPriceDTO.getAmountCanSupply());
            productsByCatalogNum.put(productQuantitiesAndPriceDTO.getProductId(),productQuantitiesAndPriceDTO.getCatalogNumber());
        }


        List<DiscountPerSupplierDTO> discountList = discountPerSupplierMapper.getSupplierDiscountsDTOs(supplierId);
        if (discountList!=null){
            for (DiscountPerSupplierDTO discountPerSupplierDTO:discountList ){
                DiscountSupplierDTO discountSupplierDTO= discountSupplierMapper.getDiscountDTO(discountPerSupplierDTO.getDiscountId());
                if (discountSupplierDTO!= null) {
                    totalDiscountSuppliers.add(new DiscountSuppliersPerOrder(discountSupplierDTO));
                }
            }
        }
        List<ProductSupplierDiscountsDTO> productSupplierDiscountsDTOList = productSupplierDiscountMapper.getDiscountDTO(supplierId);
        if (productSupplierDiscountsDTOList!=null){
            for (ProductSupplierDiscountsDTO productSupplierDiscountsDTO:productSupplierDiscountsDTOList ){
                if (productsDiscount.get(productSupplierDiscountsDTO.getProductId())==null){
                    productsDiscount.put(productSupplierDiscountsDTO.getSupplierId(),new ArrayList<>());
                }
                List <DiscountSuppliers> productDiscountSuppliers = productsDiscount.get(productSupplierDiscountsDTO.getProductId());
                DiscountSupplierDTO discountSupplierDTO= discountSupplierMapper.getDiscountDTO(productSupplierDiscountsDTO.getDiscountId());
                if (discountSupplierDTO!= null) {
                    productDiscountSuppliers.add( new DiscountSuppliersPerProduct(discountSupplierDTO));
                }
            }

        }
    }


    public boolean supplyThisDays(List<Integer> days){
        for (Integer dayInt : days){
            if (!daysCanSupply[dayInt]){
                return false;
            }
        }
        return true;

    }

    //public void SaveConstOrderFromSupplier(int day , HashMap<Integer, >)




    public boolean[] daysToArray(String daysString){
        boolean[] daysArray = new boolean[7];
        for (int i=0 ; i<7 ;i++){
            daysArray[i]=false;
        }
        for (int i=0 ; i<daysString.length() ; i++){
            daysArray[Integer.parseInt(String.valueOf(daysString.charAt(i)))]= true;
        }
        return daysArray;
    }

    public boolean isWorkOnSpecificDays() {
        return workOnSpecificDays;
    }

    public String daysToString(){
        String toReturn = "";
        for (int i=0 ; i<7 ; i++){
            if (daysCanSupply[i]){
                toReturn= toReturn + i+1;
            }
        }
        return toReturn;
    }

    public boolean isCanDeliver() {
        return canDeliver;
    }
    public void addOrder(Order order){
        orders.add(order);
    }

    public void addProductToSupply(SupplierProduct supplierProduct, int catalogNum, int amountToSupply, double price) {
        if (productsByCatalogNum.containsKey(supplierProduct)){
            throw new IllegalArgumentException("this product is all in the supplier stocks");
        }

        for (Integer productId :productsByCatalogNum.keySet()) {
            if (productsByCatalogNum.get(productId) == catalogNum) {
                throw new IllegalArgumentException("catalogNum is already assigned to another product");
            }
        }
        if (amountToSupply<=0){
            throw new IllegalArgumentException("the supplier must supply positive amount");
        }
        if (price<0){
            throw new IllegalArgumentException("price must be positive");
        }
        productsByCatalogNum.put(supplierProduct.getIdNumber(),catalogNum);
        productsQuantities.put(supplierProduct.getIdNumber(), amountToSupply);
        productsPrice.put(supplierProduct.getIdNumber(), price);
        productsDiscount.put(supplierProduct.getIdNumber(), new ArrayList<DiscountSuppliers>());
    }



    public void addContact(String name , String phoneNumber){
        sCard.addContact(new Contacts(sCard.getContactIdCounter(), name ,phoneNumber));
    }

    public void printContacts(){
        sCard.printContacts();
    }


    public void removeContact(int contactId){
        sCard.removeContact(contactId);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPaymentTerms(PaymentOptions paymentOptions) {
        this.sCard.setPaymentOptions(paymentOptions);
    }

    public void setBNNumber(int bNnumber) {
        sCard.setBNNumber(bNnumber);
    }

    public void setBankAccount(int bankAccount) {
        sCard.setBankAccountNumber(bankAccount);
    }

    public int getSupplierId() {
        return supplierId;
    }

    public SupplierCard getsCard() {
        return sCard;
    }

    public String getName() {
        return name;
    }

    //public DeliveryTerms getDeliveryTerms() {
    //    return deliveryTerms;
    //}


    public String getAddress() {
        return address;
    }

    public boolean[] getDaysCanSupply() {
        return daysCanSupply;
    }

    public List<DiscountSuppliers> getTotalDiscounts() {
        return totalDiscountSuppliers;
    }

    public int findNearestDayCanSupply(int day){
        if (!workOnSpecificDays){
            if (canDeliver){
                return 1;
            }
            else
                return 3;
        }
        for (int i=1 ; i< 8 ; i++){
            if (daysCanSupply[(i+day)%7]){
                return i-1;
            }
        }
        return -1;
    }

    public  void addDayToDeliver(int day){
        if (!workOnSpecificDays){
            throw new IllegalArgumentException("only suppliers that works on specific days can add days to deliver");
        }
        if (day<0 || day>7){
            throw new IllegalArgumentException("not a valid day;");
        }
        daysCanSupply[day]=true;
    }



    public boolean isSupplyAllTheProducts(Map<SupplierProduct,Integer> products){
        boolean ans = true;
        for (SupplierProduct supplierProduct :products.keySet()){
            ans = ans & hasEnoughOfTheProduct(supplierProduct, products.get(supplierProduct));
        }
        return ans;
    }

    public double getPriceForAllOrder(Map <SupplierProduct,Integer> productsAndAmount){
        double totalOrder = 0;
        for (SupplierProduct supplierProduct :productsAndAmount.keySet()){
            int amount = productsAndAmount.get(supplierProduct);
            totalOrder = totalOrder + (getPriceForProductAndAmount(supplierProduct,amount )*amount);
        }
        return  totalOrder  - getHighestOrderDiscount(totalOrder);
    };

    private double getBiggestDiscount(double price , DiscountSuppliers discountSuppliers){

        if (discountSuppliers.getIsPercentage()){
            return  discountSuppliers.getDiscount();
        }
        else {
            return price *(discountSuppliers.getDiscount()/100);
        }
    }

    public List<SupplierProduct> getNumOfProductsCanSupply(Map<SupplierProduct, Integer> productsToSupply){
        List<SupplierProduct> canSupply = new ArrayList<>();
        for (SupplierProduct supplierProduct :productsToSupply.keySet()){
            if (hasEnoughOfTheProduct(supplierProduct,productsToSupply.get(supplierProduct))){
                canSupply.add(supplierProduct);
            }
        }
        return canSupply;
    }

    public double getHighestOrderDiscount(double totalPrice){
        DiscountSuppliers discountSuppliers = null;
        boolean found = false;
        for (int i = 0; i< totalDiscountSuppliers.size()  && !found ; i++){
            if (totalDiscountSuppliers.get(i).getAmount()<totalPrice){
                discountSuppliers = totalDiscountSuppliers.get(i);
            }
        }
        if (discountSuppliers ==null){
            return 0.0;
        }
        else {
            return getBiggestDiscount(totalPrice, discountSuppliers);
        }
    }


    public void removeProduct(SupplierProduct SupplierProduct) {

        if(!productsByCatalogNum.containsKey(SupplierProduct)){
            throw new IllegalArgumentException("can't remove a product that the supplier doesn't has");
        }
        productsByCatalogNum.remove(SupplierProduct);
        productsPrice.remove(SupplierProduct);
        productsDiscount.remove(SupplierProduct);
        productsQuantities.remove(SupplierProduct);
    }


    public void updateProductPrice(SupplierProduct supplierProduct, double newPrice) {
        if (!productsByCatalogNum.containsKey(supplierProduct)) {
            throw new IllegalArgumentException("can't update a price to a product that the supplier doesn't has");
        }
        productsPrice.remove(supplierProduct);
        productsPrice.put(supplierProduct.getIdNumber(), newPrice);
    }

    public void addDiscountToProduct(SupplierProduct supplierProduct, DiscountSuppliers discountSuppliers) {
        if (!productsByCatalogNum.containsKey(supplierProduct)) {
            throw new IllegalArgumentException("can't add a discount to a product that the supplier doesn't has");
        }
        productsDiscount.get(supplierProduct).add(discountSuppliers);
        productsDiscount.get(supplierProduct).sort(new DiscountComparator());
    }

    public void removeDiscountToProduct(SupplierProduct supplierProduct, DiscountSuppliers discountSuppliers) {
        if (!productsByCatalogNum.containsKey(supplierProduct)) {
            throw new IllegalArgumentException("can't remove a discount from a product that the supplier doesn't has");
        }
        productsDiscount.get(supplierProduct).remove(discountSuppliers);
    }

    public void updateAmountToDiscount(SupplierProduct supplierProduct, DiscountSuppliers discountSuppliers, int amount) {
        if (!productsByCatalogNum.containsKey(supplierProduct)) {
            throw new IllegalArgumentException("can't update a discount from to a product that the supplier doesn't has");
        }
        if (!productsDiscount.containsKey(discountSuppliers)) {
            throw new IllegalArgumentException("can't update an amount to a discount that doesn't exist");
        }

        productsDiscount.get(supplierProduct).remove(discountSuppliers);
        discountSuppliers.setAmount(amount);
        productsDiscount.get(supplierProduct).add(discountSuppliers);
    }

    public void updateDiscountToDiscount(SupplierProduct supplierProduct, DiscountSuppliers discountSuppliers, double discount1) {
        if (!productsByCatalogNum.containsKey(supplierProduct)) {
            throw new IllegalArgumentException("can't update a discount from to a product that the supplier doesn't has");
        }
        if (!productsDiscount.containsKey(discountSuppliers)) {
            throw new IllegalArgumentException("can't update an amount to a discount that doesn't exist");
        }

        productsDiscount.get(supplierProduct).remove(discountSuppliers);
        discountSuppliers.setDiscount(discount1);
        productsDiscount.get(supplierProduct).add(discountSuppliers);
    }


    public Contacts getOneContacts() {
        if (sCard.getContacts().size() > 0) {
            return sCard.getContacts().get(0);
        }
        throw new IllegalArgumentException("no contacts for this supplier");
    }



    private double getPricePerProductAfterDiscount(DiscountSuppliers discountSuppliers, SupplierProduct supplierProduct, int amount) {
            return discountSuppliers.getPriceAfterDiscount(supplierProduct, productsPrice.get(supplierProduct));
    }


    public double getPriceForProductAndAmount(SupplierProduct supplierProduct, int amount) {
        List<DiscountSuppliers> discountSuppliers = productsDiscount.get(supplierProduct);
        int biggestDiscount = -1;
        for (int i = 0; i < discountSuppliers.size(); i++) {
            if (discountSuppliers.get(i) != null) {
                if (amount >= discountSuppliers.get(i).getAmount()) {
                    biggestDiscount = i;
                }
            }
        }
        if (biggestDiscount == -1) {
            return productsPrice.get(supplierProduct);
        }
        return getPricePerProductAfterDiscount(discountSuppliers.get(biggestDiscount), supplierProduct, amount);

    }

    public boolean isSupplyTheProduct(SupplierProduct supplierProduct) {
        if (productsQuantities.containsKey(supplierProduct)) {
            return true;
        }
        return false;
    }

    public double getPriceBeforeDiscount(SupplierProduct supplierProduct) {
        return productsPrice.get(supplierProduct);
    }

    public boolean hasEnoughOfTheProduct(SupplierProduct supplierProduct, int amount) {
        if(!productsQuantities.containsKey(supplierProduct)){
            return false;
        }
        return productsQuantities.get(supplierProduct) >= amount;
    }

    public int getMaxAmountPerProduct(SupplierProduct supplierProduct) {
        int maxAmount = productsQuantities.get(supplierProduct);
        return maxAmount;
    }

    public HashMap<Integer, Integer> getProductsByCatalogNum() {
        return productsByCatalogNum;
    }

    public HashMap<Integer, Double> getProductsPrice() {
        return productsPrice;
    }

    public HashMap<Integer, List<DiscountSuppliers>> getProductsDiscount() {
        return productsDiscount;
    }

    public HashMap<Integer, Integer> getProductsQuantities() {
        return productsQuantities;
    }

    public void addDiscount(DiscountSuppliers discountSuppliers){
        totalDiscountSuppliers.add(discountSuppliers);
        totalDiscountSuppliers.sort(new DiscountComparator());


    }

}

