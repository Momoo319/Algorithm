import java.util.*;

class onlineMart{

    static class Product implements Comparable<Product>{
        int id;
        int catagory;
        int company;
        int price;

        public Product(int id, int catagory, int company, int price) {
            this.id = id;
            this.catagory = catagory;
            this.company = company;
            this.price = price;
        }

        @Override
        public int compareTo (Product o) {
            return this.price == o.price ? this.id - o.id : this.price - o.price;
        }
    }

    int[][] diff;
    Map<Integer, Product> products;
    TreeSet<Product>[][] productSet = new TreeSet[6][6];

    public void init(){
        diff = new int[6][6];
        products = new HashMap<>();
        for (int category = 1; category <= 5; category++) {
            for (int company = 1; company <= 5; company++) {
                productSet[category][company] = new TreeSet<>();
            }
        }

        return;
    }

    public int sell(int mID, int mCategory, int mCompany, int mPrice){
        Product product = new Product(mID, mCategory, mCompany, mPrice - diff[mCategory][mCompany]);
        products.put(mID, product);
        productSet[mCategory][mCompany].add(product);

        return productSet[mCategory][mCompany].size();
    }

    public int closeSale(int mID){
        if (!products.containsKey(mID)) {
            return -1;
        }

        Product product = products.get(mID);
        products.remove(mID);
        productSet[product.catagory][product.company].remove(product);
        return product.price + diff[product.catagory][product.company];
    }

    public int discount(int mCategory, int mCompany, int mAmount){
        diff[mCategory][mCompany] -= mAmount;

        while (!productSet[mCategory][mCompany].isEmpty()
                && productSet[mCategory][mCompany].first().price + diff[mCategory][mCompany] <= 0) {
            products.remove(productSet[mCategory][mCompany].first().id);
            productSet[mCategory][mCompany].pollFirst();
        }
        return productSet[mCategory][mCompany].size();
    }

    Solution.RESULT show(int mHow, int mCode){
        Solution.RESULT res = new Solution.RESULT();
        TreeSet<Product> recommand = new TreeSet<>();

        for (int category = 1; category <= 5; category++) {
            if (mHow == 1 && category != mCode) continue;
            for (int company = 1; company <= 5; company++) {
                if (productSet[category][company].isEmpty() || mHow == 2 && company != mCode) continue;

                Product product = productSet[category][company].first();
                while (product != null) {
                    int price = product.price + diff[category][company];
                    if (recommand.size() < 5) {
                        recommand.add(new Product(product.id, category, company, price));
                    } else {
                        int prevId = recommand.last().id;
                        int prevPrice = recommand.last().price;

                        if (prevPrice < price || (prevPrice == price && prevId < product.id)) break;
                        recommand.pollLast();
                        recommand.add(new Product(product.id, category, company, price));
                    }

                    product = productSet[category][company].higher(product);
                }
            }
        }

        res.cnt = 0;
        while (!recommand.isEmpty()) {
            res.IDs[res.cnt++] = recommand.pollFirst().id;
        }

        return res;
    }
}