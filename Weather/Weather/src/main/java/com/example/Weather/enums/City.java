
package com.example.Weather.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum City {
    DELHI(28.6139, 77.2090),
    MUMBAI(19.0760, 72.8777),
    CHENNAI(13.0827, 80.2707),
    BANGALORE(12.9716, 77.5946),
    KOLKATA(22.5726, 88.3639),
    HYDERABAD(17.3850, 78.4867);

    private final double latitude;
    private final double longitude;
}
