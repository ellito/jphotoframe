/**
 * JPhotoFrame - a simple Java application for displaying a collection of photos in a full-screen slideshow.
 * Copyright (C) 2015  Igor Kromin
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * You can find this and my other open source projects here - http://github.com/ikromin
 */

package net.igorkromin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ikromin on 29/08/2015.
 */
public class ConfigOptions {

    private static final String PROP_DEVICE_NUM = "screenNumber";
    private static final String PROP_IMG_TIME = "imageTimeout";
    private static final String PROP_IMG_DIRECTORY = "imageDirectory";
    private static final String PROP_CACHE_DIRECTORY = "cacheDirectory";
    private static final String PROP_FONT_NAME = "fontName";
    private static final String PROP_FONT_SIZE_DATE = "fontSizeDate";
    private static final String PROP_FONT_SIZE_TIME = "fontSizeTime";
    private static final String PROP_FORMAT_TIME = "timeFormat";
    private static final String PROP_FORMAT_DATE = "dateFormat";
    private static final String PROP_DATE_OFFSET_X = "dateOffsetX";
    private static final String PROP_DATE_OFFSET_Y = "dateOffsetY";
    private static final String PROP_TIME_OFFSET_X = "timeOffsetX";
    private static final String PROP_TIME_OFFSET_Y = "timeOffsetY";
    private static final String PROP_TEXT_COLOR = "textColor";
    private static final String PROP_TEXT_OUTLINE_COLOR = "textOutlineColor";
    private static final String PROP_TEXT_SMOOTH_OUTLINE = "textSmoothOutline";

    private int gfxDeviceNum;
    private int imageTimeout;
    private int fontSizeDate;
    private int fontSizeTime;
    private int dateOffsetX;
    private int dateOffsetY;
    private int timeOffsetX;
    private int timeOffsetY;
    private int[] textColor;
    private int[] textOutlineColor;
    private boolean textSmoothOutline;
    private String imageDirectory;
    private String cacheDirectory;
    private String fontName;
    private String dateFormat;
    private String timeFormat;

    public ConfigOptions(File configFile)
    {
        System.out.println("Loading configuration properties");

        try {
            Properties props = new Properties();
            props.load(new FileInputStream(configFile));

            gfxDeviceNum = Integer.parseInt(getValue(props, PROP_DEVICE_NUM));
            imageTimeout = Integer.parseInt(getValue(props, PROP_IMG_TIME));
            fontSizeDate = Integer.parseInt(getValue(props, PROP_FONT_SIZE_DATE));
            fontSizeTime = Integer.parseInt(getValue(props, PROP_FONT_SIZE_TIME));
            dateOffsetX = Integer.parseInt(getValue(props, PROP_DATE_OFFSET_X));
            dateOffsetY = Integer.parseInt(getValue(props, PROP_DATE_OFFSET_Y));
            timeOffsetX = Integer.parseInt(getValue(props, PROP_TIME_OFFSET_X));
            timeOffsetY = Integer.parseInt(getValue(props, PROP_TIME_OFFSET_Y));
            textSmoothOutline = Boolean.parseBoolean(getValue(props, PROP_TEXT_SMOOTH_OUTLINE));
            imageDirectory = getValue(props, PROP_IMG_DIRECTORY);
            cacheDirectory = getValue(props, PROP_CACHE_DIRECTORY);
            fontName = getValue(props, PROP_FONT_NAME);
            dateFormat = getValue(props, PROP_FORMAT_DATE);
            timeFormat = getValue(props, PROP_FORMAT_TIME);
            textColor = getRgb(getValue(props, PROP_TEXT_COLOR));
            textOutlineColor = getRgb(getValue(props, PROP_TEXT_OUTLINE_COLOR));
        }
        catch (IOException e) {
            throw new RuntimeException("Cannot read configuration file: " + configFile.getAbsolutePath());
        }
        catch (Exception e) {
            throw new RuntimeException("Invalid configuration detected: " + e.getMessage());
        }
    }

    private int[] getRgb(String value) {
        String[] strArr = value.split(",");

        if (strArr.length != 3) {
            throw new RuntimeException("Expected 3 comma separated numbers, for input: " + value);
        }

        int[] rgb = new int[3];
        rgb[0] = Integer.parseInt(strArr[0]);
        rgb[1] = Integer.parseInt(strArr[1]);
        rgb[2] = Integer.parseInt(strArr[2]);

        return rgb;
    }

    private String getValue(Properties props, String key) {
        if (props.containsKey(key)) {
            String value = (String) props.get(key);
            System.out.println(key + " = " + value);

            return value;
        }
        else {
            throw new RuntimeException("Required property not found: " + key);
        }
    }

    public boolean isTextSmoothOutline() {
        return textSmoothOutline;
    }

    public int getGfxDeviceNum() {
        return gfxDeviceNum;
    }

    public String getImageDirectory() {
        return imageDirectory;
    }

    public int getImageTimeout() {
        return imageTimeout;
    }

    public String getCacheDirectory() {
        return cacheDirectory;
    }

    public int getFontSizeDate() {
        return fontSizeDate;
    }

    public int getFontSizeTime() {
        return fontSizeTime;
    }

    public String getFontName() {
        return fontName;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public int getDateOffsetX() {
        return dateOffsetX;
    }

    public int getDateOffsetY() {
        return dateOffsetY;
    }

    public int getTimeOffsetX() {
        return timeOffsetX;
    }

    public int getTimeOffsetY() {
        return timeOffsetY;
    }

    public int[] getTextColor() {
        return textColor;
    }

    public int[] getTextOutlineColor() {
        return textOutlineColor;
    }
}
