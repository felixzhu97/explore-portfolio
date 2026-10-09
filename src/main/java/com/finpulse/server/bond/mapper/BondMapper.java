package com.finpulse.server.bond.mapper;

import com.finpulse.server.bond.domain.model.Bond;
import com.finpulse.server.bond.dto.BondRequest;
import com.finpulse.server.bond.dto.BondResponse;
import org.springframework.stereotype.Component;

@Component
public class BondMapper {
  public Bond toDomain(BondRequest request) {
    return Bond.createBond(
        request.getInstrumentId(),
        request.getFaceValue(),
        request.getCouponRate(),
        request.getYtm(),
        request.getDuration(),
        request.getConvexity(),
        request.getMaturityYears(),
        request.getFrequency());
  }

  public void apply(BondRequest request, Bond bond) {
    bond.updateBond(
        request.getInstrumentId(),
        request.getFaceValue(),
        request.getCouponRate(),
        request.getYtm(),
        request.getDuration(),
        request.getConvexity(),
        request.getMaturityYears(),
        request.getFrequency());
  }

  public BondResponse toResponse(Bond bond) {
    return BondResponse.builder()
        .bondId(bond.getId().getValue())
        .instrumentId(bond.getInstrumentId().getValue())
        .faceValue(bond.getFaceValue())
        .couponRate(bond.getCouponRate())
        .ytm(bond.getYtm())
        .duration(bond.getDuration())
        .convexity(bond.getConvexity())
        .maturityYears(bond.getMaturityYears())
        .frequency(bond.getFrequency())
        .build();
  }
}
