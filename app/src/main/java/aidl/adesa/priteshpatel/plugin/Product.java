package aidl.adesa.priteshpatel.plugin;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pritesh.patel on 2016-09-09, 12:19 PM.
 * ADESA, Canada
 */
public class Product implements Parcelable{

    private String name;
    private int quantity;
    private float cost;

    public Product(String name, int quantity, float cost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }

    private Product(Parcel in) {
        name = in.readString();
        quantity = in.readInt();
        cost = in.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeInt(quantity);
        out.writeFloat(cost);
    }

    public static final Creator<Product> CREATOR
            = new Creator<Product>() {
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };


    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", cost=" + cost +
                '}';
    }
}
