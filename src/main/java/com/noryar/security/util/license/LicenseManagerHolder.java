package com.noryar.security.util.license;

import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;

/**
 * 功能描述：单例模式下的证书管理器.
 * 
 * @author Leon.
 */
public class LicenseManagerHolder {
	private static LicenseManager licenseManager;

	private LicenseManagerHolder() {
	}

	public static synchronized LicenseManager getLicenseManager(LicenseParam param) {
		if (licenseManager == null) {
			licenseManager = new LicenseManager(param);
		}
		return licenseManager;
	}
}