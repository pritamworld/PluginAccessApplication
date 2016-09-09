// IRemoteProductService.aidl
package aidl.adesa.priteshpatel.plugin;

// Declare any non-default types here with import statements
import aidl.adesa.priteshpatel.plugin.Product;
interface IRemoteProductService {
    void addProduct(String name , int quantity, float cost);
    Product getProduct(String name);
}
