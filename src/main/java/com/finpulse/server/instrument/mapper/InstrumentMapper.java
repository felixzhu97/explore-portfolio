package com.finpulse.server.instrument.mapper;

import com.finpulse.server.common.domain.model.CurrencyCode;
import com.finpulse.server.instrument.domain.model.AssetClass;
import com.finpulse.server.instrument.domain.model.Instrument;
import com.finpulse.server.instrument.dto.InstrumentRequest;
import com.finpulse.server.instrument.dto.InstrumentResponse;
import org.springframework.stereotype.Component;

@Component
public class InstrumentMapper {
  public Instrument toDomain(InstrumentRequest request) {
    return Instrument.createInstrument(
        request.getSymbol(),
        request.getName(),
        request.getAssetClass(),
        request.getCurrency(),
        request.getExchange());
  }

  public void apply(InstrumentRequest request, Instrument instrument) {
    instrument.updateInstrument(
        request.getSymbol(),
        request.getName(),
        request.getAssetClass(),
        request.getCurrency(),
        request.getExchange());
  }

  public InstrumentResponse toResponse(Instrument instrument) {
    AssetClass assetClass = instrument.getAssetClass();
    CurrencyCode currency = instrument.getCurrency();
    return InstrumentResponse.builder()
        .instrumentId(instrument.getId().getValue())
        .symbol(instrument.getSymbol().getValue())
        .name(instrument.getName())
        .assetClass(assetClass == null ? null : assetClass.name())
        .currency(currency == null ? null : currency.getCode())
        .exchange(instrument.getExchange())
        .build();
  }
}
