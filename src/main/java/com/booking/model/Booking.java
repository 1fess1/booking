package com.booking.model;

import com.booking.dto.TableSize;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import org.json.JSONObject;

@Getter
@Setter
@Builder(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    @Enumerated(EnumType.STRING)
    private TableSize tableSize;

    private LocalDate date;

    private LocalTime from;

    private LocalTime to;

    private Boolean active;

    public JSONObject toJSON() {
        return new JSONObject()
                .put("customerName", customerName)
                .put("tableSize", tableSize)
                .put("date", date)
                .put("from", from)
                .put("to", to);
    }
}
