public interface ResourceGenerator {
    boolean hasResource();
    InventoryObject pick();
    void update();
}
