/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caista.model;

/**
 *
 * @author Christian Gabriel
 */
public class SoftwareItem extends InventoryItem {

    private String licenseKey;

    public SoftwareItem(String licenseKey) {
        setLicenseKey(licenseKey);
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }
}
