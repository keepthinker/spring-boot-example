package com.keepthinker.spring.springbootexample.entity;

public class ContinentCountryPopulation {
    private IdWrapper idWrapper;
    private Long population;

    public IdWrapper getIdWrapper() {
        return idWrapper;
    }

    public void setIdWrapper(IdWrapper idWrapper) {
        this.idWrapper = idWrapper;
    }

    public String getContinent() {
        return idWrapper.continent;
    }


    public Long getPopulation() {
        return population;
    }

    public String getCountry() {
        return idWrapper.country;
    }

    private class IdWrapper {
        private String continent;
        private String country;

        public String getContinent() {
            return continent;
        }

        public void setContinent(String continent) {
            this.continent = continent;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }
}
