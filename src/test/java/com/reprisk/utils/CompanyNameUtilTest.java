package com.reprisk.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CompanyNameUtilTest {

  @Test
  public void testCleanCompanyName() {
    assertEquals("Company B", CompanyNameUtil.cleanCompanyName("Company B"));
    assertEquals("Company C", CompanyNameUtil.cleanCompanyName("(Company C)"));
    assertEquals("Company D", CompanyNameUtil.cleanCompanyName("\"Company D\""));
    assertEquals("Company E", CompanyNameUtil.cleanCompanyName("Company E Limited"));
    assertEquals("Company F", CompanyNameUtil.cleanCompanyName("Company F Ltd."));
    assertEquals("Company G", CompanyNameUtil.cleanCompanyName("Company G Inc."));
    assertEquals("Company H", CompanyNameUtil.cleanCompanyName("Company H Corp."));
    assertEquals("Company I", CompanyNameUtil.cleanCompanyName("Company I LLC"));
  }
}
