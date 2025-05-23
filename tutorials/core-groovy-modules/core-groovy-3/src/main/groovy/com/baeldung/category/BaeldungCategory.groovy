package com.baeldung.category

class BaeldungCategory {

    static String capitalize(String self) {
        String capitalizedStr = self
        if (self.size() > 0) {
            capitalizedStr = self.substring(0, 1).toUpperCase() + self.substring(1)
        }
        
        return capitalizedStr
    }

    static double toThePower(Number self, Number exponent) {
        return Math.pow(self, exponent)
    }
}
