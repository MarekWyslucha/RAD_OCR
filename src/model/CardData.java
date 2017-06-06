/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Marek
 */
public class CardData {

    private static final Pattern NAME_PATTERN = Pattern.compile("\\w{3,}\\s\\w+(-\\w+)*");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("((\\d|\\d\\s){9,11}\\s*)+");
    private static final Pattern CITY_PATTERN = Pattern.compile("\\d{2}(-|—)\\d{3}\\s*\\[A-Za-z]+");
    private static final Pattern STREET_PATTERN = Pattern.compile("(ul|al)(\\.|,)\\s*\\w+\\s*\\d+(/\\d+)?");
    private static final Pattern WEBSITE_PATTERN = Pattern.compile("(www\\.)?.*(pl|com)");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("(\\w*\\d*)+@.*(pl|com)?");

    private String name;
    private String phone;
    private String city;
    private String street;
    private String email;
    private String website;
    private String[] dataArray;

    public CardData(String data) {
        dataArray = data.split("\n");

        this.city = readPattern(CITY_PATTERN);
        this.street = readPattern(STREET_PATTERN);
        this.website = readPattern(WEBSITE_PATTERN);
        this.email = readPattern(EMAIL_PATTERN);
        this.phone = readPattern(NUMBER_PATTERN);
        this.name = readPattern(NAME_PATTERN);
    }

    private String readPattern(Pattern pattern) {
        for (int i = 0; i < dataArray.length; i++) {
            Matcher matcher = pattern.matcher(dataArray[i]);
            if (matcher.find()) {
                dataArray[i] = dataArray[i].replace(pattern.toString(), "");
                return matcher.group(0);
            }
        }
        return "";
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

}
