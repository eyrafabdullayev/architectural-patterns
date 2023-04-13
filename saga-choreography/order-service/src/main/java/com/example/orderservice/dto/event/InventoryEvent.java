package com.example.orderservice.dto.event;

import com.example.orderservice.dto.InventoryDto;
import com.example.orderservice.enums.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEvent implements Event, Serializable {

    private UUID eventId;
    private Date date;
    private InventoryDto inventoryDto;
    private InventoryStatus inventoryStatus;

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return date;
    }
}
