package dev.modusami.invmanagementsys.controller;

import dev.modusami.invmanagementsys.enums.Status;
import dev.modusami.invmanagementsys.model.InventoryItem;
import dev.modusami.invmanagementsys.model.InventoryItemId;
import dev.modusami.invmanagementsys.model.Product;
import dev.modusami.invmanagementsys.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService = new InventoryService();

    public InventoryController() {

    }

    @PostMapping
    public ResponseEntity<InventoryItemId> addItem(@RequestBody Product product) {
        InventoryItem item = new InventoryItem(product, new InventoryItemId());
        Status status = inventoryService.addItem(item);
        if (status == Status.SUCCESS) {
            return ResponseEntity.ok(item.getId());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable InventoryItemId id) {
        Status status = inventoryService.deleteItem(id);
        if (status == Status.SUCCESS) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<String> updateItem(@RequestBody InventoryItem item){
        if(inventoryService.updateItem(item) == Status.SUCCESS){
            return ResponseEntity.ok("OKAY");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<InventoryItem> getItem(@PathVariable String id) {

        UUID uuid = UUID.fromString(id);
        InventoryItemId inventoryItemId = new InventoryItemId(uuid);
        InventoryItem item = inventoryService.getItem(inventoryItemId);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<InventoryItem>> getItemsByName(@PathVariable String name) {
        List<InventoryItem> items = inventoryService.getItemsByName(name);
        if (!items.isEmpty()) {
            return ResponseEntity.ok(items);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<InventoryItem>> getItemsByCategory(@PathVariable String category)
    {
        List<InventoryItem> items = inventoryService.getItemsByCategory(category);
        if (!items.isEmpty()) {
            return ResponseEntity.ok(items);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Map<InventoryItemId, InventoryItem>> getInventory() {
        Map<InventoryItemId, InventoryItem> inventory = inventoryService.getInventory();
        return ResponseEntity.ok(inventory);
    }
}