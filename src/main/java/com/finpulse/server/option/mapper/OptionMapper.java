package com.finpulse.server.option.mapper;

import com.finpulse.server.option.domain.model.Option;
import com.finpulse.server.option.dto.OptionRequest;
import com.finpulse.server.option.dto.OptionResponse;
import org.springframework.stereotype.Component;

@Component
public class OptionMapper {
  public Option toDomain(OptionRequest request) {
    return Option.createOption(
        request.getInstrumentId(),
        request.getUnderlyingInstrumentId(),
        request.getStrike(),
        request.getExpiry(),
        request.getOptionType(),
        request.getRiskFreeRate(),
        request.getVolatility(),
        request.getBsPrice(),
        request.getDelta(),
        request.getGamma(),
        request.getTheta(),
        request.getVega(),
        request.getRho(),
        request.getImpliedVolatility());
  }

  public void apply(OptionRequest request, Option option) {
    option.updateOption(
        request.getInstrumentId(),
        request.getUnderlyingInstrumentId(),
        request.getStrike(),
        request.getExpiry(),
        request.getOptionType(),
        request.getRiskFreeRate(),
        request.getVolatility(),
        request.getBsPrice(),
        request.getDelta(),
        request.getGamma(),
        request.getTheta(),
        request.getVega(),
        request.getRho(),
        request.getImpliedVolatility());
  }

  public OptionResponse toResponse(Option option) {
    return OptionResponse.builder()
        .optionId(option.getId().getValue())
        .instrumentId(option.getInstrumentId().getValue())
        .underlyingInstrumentId(option.getUnderlyingInstrumentId().getValue())
        .strike(option.getStrike())
        .expiry(option.getExpiry())
        .optionType(option.getOptionType().name())
        .riskFreeRate(option.getRiskFreeRate())
        .volatility(option.getVolatility())
        .bsPrice(option.getBsPrice())
        .delta(option.getDelta())
        .gamma(option.getGamma())
        .theta(option.getTheta())
        .vega(option.getVega())
        .rho(option.getRho())
        .impliedVolatility(option.getImpliedVolatility())
        .build();
  }
}
