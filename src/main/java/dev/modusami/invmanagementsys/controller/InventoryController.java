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
import java.util.Map;

@CrossOrigin("*") // to allow from all domains
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

    @PutMapping("/{id}/name")
    public ResponseEntity<Void> editItemName(@PathVariable InventoryItemId id, @RequestBody String newName) {
        Status status = inventoryService.editItemName(id, newName);
        if (status == Status.SUCCESS) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/category")
    public ResponseEntity<Void> editItemCategory(@PathVariable InventoryItemId id, @RequestBody String newCategory) {
        Status status = inventoryService.editItemCategory(id, newCategory);
        if (status == Status.SUCCESS) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/description")
    public ResponseEntity<Void> editItemDescription(@PathVariable InventoryItemId id, @RequestBody String newDescription) {
        Status status = inventoryService.editItemDescription(id, newDescription);
        if (status == Status.SUCCESS) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryItem> getItem(@PathVariable InventoryItemId id) {
        InventoryItem item = inventoryService.getItem(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<Map<InventoryItemId, InventoryItem>> getInventory() {
        Map<InventoryItemId, InventoryItem> inventory = inventoryService.getInventory();
        return ResponseEntity.ok(inventory);
    }
}