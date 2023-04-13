package com.example.inventoryservice.dto.event;

import com.example.inventoryservice.dto.InventoryDto;
import com.example.inventoryservice.enums.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEvent implements Event {

    private UUID eventId = UUID.randomUUID();
    private Date date = new Date();
    private InventoryDto inventoryDto;
    private InventoryStatus inventoryStatus;

    public static InventoryEvent of(InventoryDto inventory, InventoryStatus inventoryStatus) {
        return InventoryEvent.builder()
                .inventoryDto(inventory)
                .inventoryStatus(inventoryStatus)
                .build();
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return date;
    }
}
