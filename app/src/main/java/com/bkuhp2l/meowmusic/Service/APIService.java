package com.bkuhp2l.meowmusic.Service;

public class APIService {
    private static String base_url = "https://adhppl99-1.000webhostapp.com/Server/";
    public static DataService getService() {
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
