package com.finpulse.server.preference.mapper;

import com.finpulse.server.preference.domain.model.UserPreference;
import com.finpulse.server.preference.dto.UserPreferenceRequest;
import com.finpulse.server.preference.dto.UserPreferenceResponse;
import org.springframework.stereotype.Component;

@Component
public class UserPreferenceMapper {

  public UserPreference toDomain(UserPreferenceRequest request) {
    return UserPreference.createPreference(
        request.getCustomerId(),
        request.getTheme(),
        request.getLanguage(),
        request.isNotificationsEnabled());
  }

  public void apply(UserPreferenceRequest request, UserPreference preference) {
    preference.updatePreference(
        request.getCustomerId(),
        request.getTheme(),
        request.getLanguage(),
        request.isNotificationsEnabled());
  }

  public UserPreferenceResponse toResponse(UserPreference preference) {
    return UserPreferenceResponse.builder()
        .preferenceId(preference.getId().getValue())
        .customerId(preference.getCustomerId().getValue())
        .theme(preference.getTheme())
        .language(preference.getLanguage())
        .notificationsEnabled(preference.isNotificationsEnabled())
        .updatedAt(preference.getUpdatedAt())
        .build();
  }
}
