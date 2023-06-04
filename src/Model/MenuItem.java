package Model;

public class MenuItem {
        private int ItemId;
        private String ItemName;
        private String ItemCategory;
        private double ItemPrice;

        public MenuItem(){

        }
        public MenuItem(int ItemId, String ItemName, String ItemCategory, double ItemPrice) {
            this.ItemId = ItemId;
            this.ItemName = ItemName;
            this.ItemCategory = ItemCategory;
            this.ItemPrice = ItemPrice;
        }

        public int getItemId() {
            return ItemId;
        }

        public String getItemName() {
            return ItemName;
        }

        public String getItemCategory() {
            return ItemCategory;
        }

        public double getItemPrice() {
            return ItemPrice;
        }

        public void setItemId(int ItemId) {
            this.ItemId = ItemId;
        }
        public void setItemName(String ItemName) {
            this.ItemName = ItemName;
        }
        public void setItemCategory(String ItemCategory) {
            this.ItemCategory = ItemCategory;
        }
        public void setItemPrice(double ItemPrice) {
            this.ItemPrice = ItemPrice;
        }

}
