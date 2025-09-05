package com.chellefulk.api.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class GoogleSheetsService {
    private static final String APPLICATION_NAME = "ChelleFulk";
        private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SPREADSHEET_ID = "1Pmz9yYFHda8BEStgbcoXwOYA30k5T9rQ-F6608O6id4";
    private static final String SHEET_NAME = "Sheet1"; // Change if needed

    public List<Map<String, Object>> getSchedule() throws Exception {
        InputStream in = getClass().getResourceAsStream("/google-service-account.json");
        if (in == null) {
            throw new IllegalStateException("google-service-account.json not found in classpath. Please place it in src/main/resources.");
        }
        GoogleCredentials credentials = GoogleCredentials.fromStream(in)
            .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS_READONLY));
        Sheets sheetsService = new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                new HttpCredentialsAdapter(credentials)
        ).setApplicationName(APPLICATION_NAME).build();

        String range = SHEET_NAME;
        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.size() <= 1) {
            return Collections.emptyList();
        }
        // Get header row
        List<Object> header = values.get(0);
        List<Map<String, Object>> result = new ArrayList<>();
        for (int i = 1; i < values.size(); i++) {
            List<Object> row = values.get(i);
            Map<String, Object> obj = new HashMap<>();
            // Map Google Sheets columns to GigsDTO keys
            obj.put("date", getValue(header, row, "Date"));
            obj.put("ensemble", getValue(header, row, "Ensemble"));
            obj.put("event", getValue(header, row, "Event"));
            obj.put("ticketsOrInfoLink", getValue(header, row, "Tickets or info link"));
            obj.put("times", getValue(header, row, "Times"));
            obj.put("venue", getValue(header, row, "Venue"));
            obj.put("notes", getValue(header, row, "Notes"));
            result.add(obj);
        }
        return result;
    }

    // Helper to get value by column name
    private Object getValue(List<Object> header, List<Object> row, String columnName) {
        for (int j = 0; j < header.size(); j++) {
            if (header.get(j).toString().equalsIgnoreCase(columnName)) {
                return j < row.size() ? row.get(j) : "";
            }
        }
        return "";
    }
}
