package com.Business;

import com.DAL.DAO.ProductQuantitiesAndPriceMapper;
import com.DAL.DAO.SupplierMapper;
import com.DAL.DTO.ProductQuantitiesAndPriceDTO;
import com.DAL.DTO.SupplierDTO;

import java.util.*;
import java.time.DayOfWeek;





public class SuppliersController {
    private Map<Integer, Supplier> supplierMapMap;
    //private Map<Integer, SupplierProduct> systemProducts;
    private Map<Integer, BranchSup> branchMap;
    private List<Order> lastOrder;
    private List<Order> allOrders;

    private HashMap<Integer , HashMap<Integer ,Supplier>> constOrdersBydays_byOrserId;
    //private HashMap<Integer, List<Order>> ordersBySuppliers;
    private int orderIdCounter;
    private int discountCounter;
    SupplierMapper supplierMapper;
    ProductQuantitiesAndPriceMapper productQuantitiesAndPriceMapper;

    private HashMap<Integer, Order> constOrders;
    private int periodOrderCounter;

    private SupplierMapper SupplierMapper;


    public SuppliersController() {
        supplierMapMap = new HashMap<>();
        lastOrder = new ArrayList<>();
        allOrders = new ArrayList<>();
        this.branchMap = new HashMap<>();
        //systemProducts = new HashMap<>();
        this.orderIdCounter = 1;
        this.discountCounter = 1;
        SupplierMapper = new SupplierMapper();
        productQuantitiesAndPriceMapper = new ProductQuantitiesAndPriceMapper();


    }

   /* public void addProductToTheSystem(int idNumber, String name, String producer) {
        if (systemProducts.get(idNumber) != null) {
            throw new IllegalArgumentException("this Id is already associated to another product");
        }
        SupplierProduct newSupplierProduct = new SupplierProduct(idNumber, name, producer);
        systemProducts.put(idNumber, newSupplierProduct);
    }*/

    /*public void removeProductFromTheSystem(int idNumber){
        if (!systemProducts.containsKey(idNumber)) {
            throw new IllegalArgumentException("can't remove a product that isn't in the system");
        }

        systemProducts.remove(idNumber);
    }*/

    public void addDiscountToProduct(int supplierId, SupplierProduct product, int amount, double discount, boolean percentageDiscount) {
        if (!containSupplier(supplierId)) {
            throw new IllegalArgumentException("this supplierId doesn't exist in the system");
        }
        //if (!containProduct(productID)) {
        //    throw new IllegalArgumentException("this product ID doesn't exist in the system");
        //}
        if (!supplierContainSProduct(supplierId, product)) {
            throw new IllegalArgumentException("this supplier does not supply this product");
        }

        getSupplier(supplierId).addDiscountToProduct(product, new DiscountSuppliersPerProduct(percentageDiscount, amount, discount, discountCounter++));
    }

    public void addDiscountToOrder(int supplierId, double amount, double discount, boolean percentageDiscount) {
        if (!containSupplier(supplierId)) {
            throw new IllegalArgumentException("this supplierId doesn't exist in the system");
        }
        getSupplier(supplierId).addDiscount(new DiscountSuppliersPerOrder(percentageDiscount, amount, discount, discountCounter++));
    }


    public void addBranch(int branchId, String branchName, String branchAddress) {
        if (branchMap.containsKey(branchId)) {
            throw new IllegalArgumentException("this branch Id is already exist");
        }
        branchMap.put(branchId, new BranchSup(branchName, branchId, branchAddress));
    }

    public void addSupplier(int supplierId, boolean canDeliver, String workDays, int BNNumber, int BankAccount, PaymentOptions pOpt, String name, String contactName, String phoneNumber, String address) {
        if (containSupplier(supplierId)) {
            throw new IllegalArgumentException("this supplier is already in the system");
        }
        //supp.put(supplierId, new ArrayList<>());
        //ArrayList<Contacts> contactsList = new ArrayList<>();
        supplierMapMap.put(supplierId, new Supplier(supplierId, new SupplierCard(BNNumber, BankAccount, pOpt, new Contacts(1, contactName, phoneNumber)), canDeliver, workDays, name, address));
    }

    public void removeSupplier(int supplierId) {
        if (!supplierMapMap.containsKey(supplierId)) {
            throw new IllegalArgumentException("this supplier number that you have enter is not exist in the system"
                    + "please check the number and try again");
        } else {
            supplierMapMap.remove(supplierId);
        }
    }

    public void printLastOrder() {
        if (lastOrder.size() == 0) {
            throw new IllegalArgumentException("no orders in the system");
        }
        for (Order order : lastOrder) {
            order.printOrder();
        }
    }

    public void editSupplierName(int supplierId, String name) {
        if (!containSupplier(supplierId)) {
            throw new IllegalArgumentException("this supplier Id does not exist");
        }
        supplierMapMap.get(supplierId).setName(name);
    }

    /*public void editSupplierDeliveryTerms(int supplierId, boolean ha) {
        if (!containSupplier(supplierId)) {
            throw new IllegalArgumentException("this supplier Id does not exist");
        }
        supplierMapMap.get(supplierId).setDeliveryTerms(newDeliveryTerms);
    }*/

    public void editSupplierPaymentTerms(int supplierId, PaymentOptions paymentOptions) {
        if (!containSupplier(supplierId)) {
            throw new IllegalArgumentException("this supplier Id does not exist");
        }
        supplierMapMap.get(supplierId).setPaymentTerms(paymentOptions);
    }

    public void editBNNumber(int supplierId, int bnNumber) {
        if (!containSupplier(supplierId)) {
            throw new IllegalArgumentException("this supplier Id does not exist");
        }
        supplierMapMap.get(supplierId).setBNNumber(bnNumber);
    }

    public void editBankAccount(int supplierId, int bankAccount) {
        if (!containSupplier(supplierId)) {
            throw new IllegalArgumentException("this supplier Id does not exist");
        }
        supplierMapMap.get(supplierId).setBankAccount(bankAccount);
    }

    private boolean containSupplier(int supplierID) {
        boolean exist = supplierMapMap.containsKey(supplierID);
        if (exist)
            return true;
        SupplierDTO supplierDTO = supplierMapper.selectSupplier(supplierID);
        if (supplierDTO == null) {
            return false;
        }
        Supplier supplier = new Supplier(supplierDTO);
        supplierMapMap.put(supplierID, supplier);
        return true;
    }

    /*private boolean containProduct(int productId) {
        return systemProducts.containsKey(productId);
    }*/

    private boolean supplierContainSProduct(int supplierId, SupplierProduct product) {
        boolean exist = getSupplier(supplierId).isSupplyTheProduct(product);
        if (exist) {
            return true;
        }
        ProductQuantitiesAndPriceDTO productQuantitiesAndPriceDTO = productQuantitiesAndPriceMapper.selectProductBySupplier(supplierId, product.getIdNumber());
        if (productQuantitiesAndPriceDTO == null) {
            return false;
        }
        getSupplier(supplierId).addProductToSupply(product, productQuantitiesAndPriceDTO.getCatalogNumber(), productQuantitiesAndPriceDTO.getAmountCanSupply(), productQuantitiesAndPriceDTO.getPrice());
        return true;
    }


    public Supplier getSupplier(int supplierId) {
        if (!containSupplier(supplierId)) {
            throw new IllegalArgumentException("this supplier Id does not exist");
        }
        return supplierMapMap.get(supplierId);
    }


    public void editOrder(Order order, ProductInOrder product, int newAmount) {
        order.editProductInOrderAmount(product, newAmount);
    }


    /*public void addProductToExistingOrder(Order order, Product product, int amount) {
        if(order.getCurrOrderStatus()!= Order.OrderStatus.not_order_yet) {
            throw new IllegalArgumentException("order has already sent");
        }
        Supplier supplier1 = order.getSupplier();
        int catalogNum = supplier1.getProductsByCatalogNum().get(product);
        order.AddProductToOrder(product, amount,catalogNum );
    }*/

    public void sendOrder(Order order) {
        order.sendOrder();
    }

    public void printSuppliers() {
        for (int supplierId : supplierMapMap.keySet()) {
            Supplier supplier = supplierMapMap.get(supplierId);
            System.out.println("supplier Id : " + supplier.getSupplierId() + " supplierName : " + supplier.getName());
        }
    }

    public void removeProductFromSupplier(int supplierId, SupplierProduct product) {
        if (!containSupplier(supplierId)) {
            throw new IllegalArgumentException("this supplier Id does not exist in the system");
        }
        if (product == null) {
            System.out.println("this product Id does not exist in the system , please add it to the system ant then try again");
        }
        getSupplier(supplierId).removeProduct(product);
    }


    public void addProductToSupplier(int supplierId, SupplierProduct product, int catalogNum, int amountToSupply, double price) {
        if (!containSupplier(supplierId)) {
            throw new IllegalArgumentException("this supplier Id does not exist in the system");
        }
        if (product == null) {
            System.out.println("this product Id does not exist in the system , please add it to the system ant then try again");
        }
        supplierMapMap.get(supplierId).addProductToSupply(product, catalogNum, amountToSupply, price);
    }

    /*private SupplierProduct getProductByProductId(int productId) {
        if (!containProduct(productId)) {
            System.out.println("this product Id doesn't exist in the system");
        }
        return systemProducts.get(productId);
    }*/


    public Map<Supplier, Integer> getBestPriceSuppliers(SupplierProduct supplierProduct, int amount) {
        List<Supplier> relevantSuppliers = new ArrayList<>();
        List<Double> relevantSuppliersPrices = new ArrayList<>();
        for (Supplier supplier : supplierMapMap.values()) {
            if (supplier.isSupplyTheProduct(supplierProduct)) {
                int amount1 = supplier.getProductsQuantities().get(supplierProduct);
                relevantSuppliers.add(supplier);
                relevantSuppliersPrices.add(supplier.getPriceForProductAndAmount(supplierProduct, amount1));
            }
        }
        int totalAmountCanSupply = 0;
        for (Supplier supplier : relevantSuppliers) {
            totalAmountCanSupply = totalAmountCanSupply + supplier.getMaxAmountPerProduct(supplierProduct);
        }
        if (totalAmountCanSupply < amount) {
            throw new IllegalArgumentException("this product amount cannot supply by the suppliers");
        }
        Map<Supplier, Integer> toOrderSuppliers = new HashMap<>();
        while (amount > 0) {
            Supplier minSupplier = getMultiBestPriceSupplier(relevantSuppliers, relevantSuppliersPrices, supplierProduct.getIdNumber(), amount);
            toOrderSuppliers.put(minSupplier, Math.min(minSupplier.getMaxAmountPerProduct(supplierProduct), amount));
            amount = amount - minSupplier.getMaxAmountPerProduct(supplierProduct);
        }

        return toOrderSuppliers;
    }

    private Supplier getMultiBestPriceSupplier(List<Supplier> relevantSuppliers, List<Double> relevantSuppliersPrices, int productId, int amount) {
        double minPrice = relevantSuppliersPrices.get(0);
        Supplier minSupplier = relevantSuppliers.get(0);
        int minIndex = 0;
        for (int i = 1; i < relevantSuppliersPrices.size(); i++) {
            if (relevantSuppliersPrices.get(i) < minPrice) {
                minIndex = i;
                minSupplier = relevantSuppliers.get(i);
            }
        }
        relevantSuppliers.remove(minSupplier);
        relevantSuppliersPrices.remove(minPrice);
        return minSupplier;
    }


    // add new Order to the System
    private void addOrderFromSpecificSupplier(int branchId, int supplierId, Map<SupplierProduct, Integer> products) {
        Supplier currSupplier = supplierMapMap.get(supplierId);
        Order currOrder = new Order(currSupplier, branchId, branchMap.get(branchId).getAddress(), orderIdCounter++);
        for (SupplierProduct supplierProduct : products.keySet()) {
            int catalogNum = currSupplier.getProductsByCatalogNum().get(supplierProduct);
            currOrder.AddProductToOrder(supplierProduct, products.get(supplierProduct), catalogNum);
        }
        double orderTotalPrice = currOrder.getTotalPrice();
        orderTotalPrice = orderTotalPrice - currSupplier.getHighestOrderDiscount(orderTotalPrice);
        currOrder.setTotalPrice(orderTotalPrice);

        this.lastOrder.add(currOrder);
        this.allOrders.add(currOrder);
        //List<Order> orders = ordersBySuppliers.get(currSupplier.getSupplierId());
        //orders.add(currOrder);
        getSupplier(supplierId).addOrder(currOrder);
    }

    public ArrayList<Supplier>[] SuppliersNextSupplyDayArray() {
        ArrayList<Supplier>[] arrayToReturn = new ArrayList[7];
        //ArrayList<Supplier> arrayToReturn =new ArrayList<Supplier>();
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(calendar.DAY_OF_WEEK);
        for (int supplierId : supplierMapMap.keySet()) {
            Supplier supplier = getSupplier(supplierId);
            int nearestDay = supplier.findNearestDayCanSupply(today);
            arrayToReturn[nearestDay].add(supplier);
        }
        return arrayToReturn;
    }
    public void loadAllSuppliers(){
        List<SupplierDTO> allSuppliers = SupplierMapper.selectAllSuppliers();
        for (SupplierDTO supplierDTO: allSuppliers){
            if (!supplierMapMap.containsKey(supplierDTO.getId())){
                Supplier supplier = new Supplier(supplierDTO);
                supplierMapMap.put(supplierDTO.getId(),supplier);
            }
        }
    }

    public void addShortageOrder(HashMap<SupplierProduct, Integer> productsAndAmount, BranchSup branchSup) {
        loadAllSuppliers();
        ArrayList<Supplier>[] nearestDAysArray = SuppliersNextSupplyDayArray();
        ArrayList<Supplier> canSupplyAlone = new ArrayList<>();
        //ArrayList<Supplier> canSupplyAllPartial = new ArrayList<>();
        int dayToAllOrder=7;
        lastOrder = new ArrayList<>();
        boolean found= false;
        /// check if there is a supplier who can supply all order alone ,
        // if exist it save the nearest day;
        for (int i = 0; i < 7 & !found; i++) {
            List<Integer> canSupplyAllOrder = new ArrayList<>();
            for (Supplier supplier : nearestDAysArray[i]) {
                if (supplier.isSupplyAllTheProducts(productsAndAmount)) {
                    canSupplyAlone.add(supplier);
                    found = true;
                    dayToAllOrder =i;
                }
            }
        }
        Map<Supplier, Map<SupplierProduct, Integer>> productsBySuppliers= new HashMap<>();
        ArrayList<Supplier> relevantSuppliers ;
        HashMap<SupplierProduct, Integer> productsAndAmountCopy= new HashMap<>(productsAndAmount);
        for (int i= 0 ; i< dayToAllOrder  ; i++ ) {
            relevantSuppliers = nearestDAysArray[i];
            while (productsAndAmountCopy.keySet().size() > 0) {
                int supplierId = getWhoSupplyTheMost(productsAndAmountCopy, relevantSuppliers);
                if (supplierId != -1) {
                    List<SupplierProduct> canSupplyList = getSupplier(supplierId).getNumOfProductsCanSupply(productsAndAmountCopy);
                    Map<SupplierProduct, Integer> productIntegerMap = shareTheOrder(productsAndAmountCopy, canSupplyList);
                    if (!productsBySuppliers.containsKey(getSupplier(supplierId))) {
                        productsBySuppliers.put(getSupplier(supplierId), productIntegerMap);
                    } else {
                        for (SupplierProduct supplierProduct : productIntegerMap.keySet()) {
                            productsBySuppliers.get(getSupplier(supplierId)).put(supplierProduct, productIntegerMap.get(supplierProduct));
                        }
                    }
                } else {
                    for (SupplierProduct supplierProduct : productsAndAmountCopy.keySet()) {
                        Map<Supplier, Integer> suppliersProducts = shareTheProductOrder(supplierProduct, productsAndAmountCopy.get(supplierProduct));
                        for (Supplier supplier : suppliersProducts.keySet()) {
                            if (productsBySuppliers.containsKey(supplier)) {
                                productsBySuppliers.get(supplier).put(supplierProduct, suppliersProducts.get(supplier));
                            } else {
                                HashMap<SupplierProduct, Integer> currSupplierHashMap = new HashMap<SupplierProduct, Integer>();
                                currSupplierHashMap.put(supplierProduct, suppliersProducts.get(supplier));
                                productsBySuppliers.put(supplier, currSupplierHashMap);
                            }
                        }
                        productsAndAmountCopy.remove(supplierProduct);
                    }
                }
            }
        }
        if (productsAndAmountCopy.keySet().size()>0){
            int minSupplierId = -1;
            double minOrder = Integer.MAX_VALUE;
            for (Supplier supplier: canSupplyAlone) {
                double currPrice = supplier.getPriceForAllOrder(productsAndAmount);
                if (minOrder > currPrice) {
                    minOrder = currPrice;
                    minSupplierId = supplier.getSupplierId();
                }
            }
            if (minSupplierId!=-1){
                addOrderFromSpecificSupplier(branchSup.getBranchId(),minSupplierId, productsAndAmount);
            }
            else {
                throw new IllegalArgumentException("cannot supply the order");
            }
        }
        else{
            for (Supplier supplier: productsBySuppliers.keySet()) {
                addOrderFromSpecificSupplier(branchSup.getBranchId(),supplier.getSupplierId(), productsBySuppliers.get(supplier) );
            }
        }

    }


    public void addAutomaticOrderConst(List<Integer> constantDays, HashMap<SupplierProduct,Integer> items, int branchId) throws Exception {
        // check in dto that branch exists
        loadAllSuppliers();
        List<Supplier> constSuppliers = getConstSuppliers();
        List<Supplier> constSuppliersMatchingDays = new ArrayList<>();
        for (Supplier supplier: constSuppliers){
            if (supplier.supplyThisDays(constantDays)){
                constSuppliersMatchingDays.add(supplier);
            }
        }

        Map<SupplierProduct, Integer> currProducts = new HashMap<>(items);
        List<Integer> canSupplyAllOrder = new ArrayList<>();
        for (Supplier supplier1 : constSuppliersMatchingDays) {
            if (supplier1.isSupplyAllTheProducts(currProducts)) {
                canSupplyAllOrder.add(supplier1.getSupplierId());
            }
        }
        int minSupplierId = -1;
        double minOrder = Integer.MAX_VALUE;
        for (int supplierId : canSupplyAllOrder) {
            double currPrice = getSupplier(supplierId).getPriceForAllOrder(currProducts);
            if (minOrder > currPrice) {
                minOrder = currPrice;
                minSupplierId = supplierId;
            }
        }
        if (minSupplierId == -1) {
           throw new IllegalArgumentException("no supplier can supply");
        }
        Supplier supplier1 = supplierMapMap.get(minSupplierId);
        //supplier1.SaveConstOrderFromSupplier();
        //addOrderConst(supplier1,constantDays,items, branchId, periodOrderCounter++);

    }


    public Order addOrderConst(Supplier supplier,List<DayOfWeek> supplyConstantDays,HashMap<SupplierProduct, Integer> productsAndAmount, int branchId,int orderID) throws Exception {

        HashMap<SupplierProduct, Integer> itemAndAmount = new HashMap<>();
        HashMap<Integer,HashMap<Integer,Integer>> itemToAmountAndDiscount = new HashMap<>();

        //Order orderConst = orderDAO.getOrderConst(orderID);
        // validateOrderIsNull(orderConst);
        String days = "";
        for (DayOfWeek day : supplyConstantDays)
            days = days.equals("")? day.toString() : days + " "+day;
        Order orderConst = new OrderConst(supplier, branchId, getBranchMap().get(branchId).getAddress(),orderID, supplyConstantDays);
        //orderDAO.insertOrderConst(supplierID, orderID, orderConst.getPriceAfterDiscount(), days, superAddress);
        //for(Item item : itemAndAmount.keySet())
          //  orderDAO.insertOrderItems(item.getItemID(), orderConst.getOrderID(), itemAndAmount.get(item), orderConst.calculatePrice(itemAndAmount.get(item),item.getPrice(),itemToAmountAndDiscount.get(item.getItemID())),item.getName());
        return orderConst;
    }

    public List<Supplier> getConstSuppliers() {
        List<Supplier> supplierList = new ArrayList<>();
        for(Integer supplierId: supplierMapMap.keySet()){
            supplierList.add(supplierMapMap.get(supplierId));
        }
        List<Supplier> suppliersConstList = new LinkedList<>();
        for(Supplier s : supplierList)
            if(s.isWorkOnSpecificDays())
                suppliersConstList.add(s);
        return suppliersConstList;
    }

    private int getWhoSupplyTheMost(Map<SupplierProduct, Integer> productsToOrder, ArrayList<Supplier> relevantSuppliers) {
        int maxProducts = 0;
        int minSupplierId = -1;
        double price = Integer.MAX_VALUE;
        for (Supplier supplier : relevantSuppliers) {
            List<SupplierProduct> canSupply = supplier.getNumOfProductsCanSupply(productsToOrder);
            int currNum = canSupply.size();
            HashMap<SupplierProduct, Integer> canSupplyMap = new HashMap<>();
            for (int i=0; i<canSupply.size();i++){
                canSupplyMap.put(canSupply.get(i), productsToOrder.get(canSupply.get(i)));
            }
            if (currNum > maxProducts) {
                maxProducts = currNum;
                minSupplierId = supplier.getSupplierId();
                price =supplier.getPriceForAllOrder(canSupplyMap);
            }
            else if (currNum == maxProducts &currNum!=0) {
                double currPrice = supplier.getPriceForAllOrder(canSupplyMap);
                if (price > currPrice ) {
                    maxProducts = currNum;
                    minSupplierId = supplier.getSupplierId();
                    price = currPrice;
                }
            }
        }
        return minSupplierId;
    }


    private Map<SupplierProduct, Integer> shareTheOrder(Map<SupplierProduct, Integer> productsToOrder, List<SupplierProduct> canSupplyList) {
        Map<SupplierProduct, Integer> biggestOrder = new HashMap<>();
        List<SupplierProduct> toRemove = new ArrayList<>();
        for (SupplierProduct supplierProduct : productsToOrder.keySet()) {
            if (canSupplyList.contains(supplierProduct)) {
                biggestOrder.put(supplierProduct, productsToOrder.get(supplierProduct));
                toRemove.add(supplierProduct);
            }

        }
        for(int i=0; i< toRemove.size();i++){
            productsToOrder.remove(toRemove.get(i));
        }
        return biggestOrder;
    }
    private Map<Supplier , Integer> shareTheProductOrder(SupplierProduct supplierProduct, int amount) {
        Map<Supplier, Integer> currSuppliers = getBestPriceSuppliers(supplierProduct, amount);
        return currSuppliers;
    }



    //////////////////////////////////////////////
    // Supplier's edit functions
    public void addContact (int supplierId, String contactName , String phoneNumber ){
        if (!supplierMapMap.containsKey(supplierId)){
            throw new IllegalArgumentException("can't add a contact member to a supplier that isn't in the system");
        }
        getSupplier(supplierId).addContact(contactName , phoneNumber);
    }

    public void removeContact(int supplierId, int contactId){
        if (!supplierMapMap.containsKey(supplierId)){
            throw new IllegalArgumentException("can't remove a contact member to a supplier that isn't in the system");
        }
        getSupplier(supplierId).removeContact(contactId);
    }

    public void printSupplierContacts(int supplierId){
        getSupplier(supplierId).printContacts();
    }



    public  Map<Integer, Supplier> getSupplierMapMap(){
        return supplierMapMap;
    }

    //public  Map<Integer, SupplierProduct> getSystemProduct(){
    //    return systemProducts;
   // }


    public Map<Integer, BranchSup> getBranchMap(){
        return branchMap;
    }

    public List<Order> getAllOrders(){
        return allOrders;
    }

    public List<Order> getLastOrder(){
        return lastOrder;
    }





}
