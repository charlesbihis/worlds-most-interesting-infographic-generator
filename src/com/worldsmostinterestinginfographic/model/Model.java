/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.worldsmostinterestinginfographic.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Logger;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

public enum Model {
  INSTANCE;

  public static final String CLIENT_ID;
  public static final String CLIENT_SECRET;
  public static final String REDIRECTION_ENDPOINT;
  public static final String AUTHORIZATION_ENDPOINT;
  public static final String TOKEN_ENDPOINT;

  public static final String FACEBOOK_API_ENDPOINT;
  public static final String FACEBOOK_REQUESTED_PROFILE_FIELDS;
  public static final String FACEBOOK_REQUESTED_FEED_FIELDS;
  public static final int MAX_NUMBER_OF_FACEBOOK_POSTS_TO_REQUEST;

  public static Cache cache;

  private static final Logger log = Logger.getLogger(Model.INSTANCE.getClass().getName());

  static {
    // Initialize the cache
    try {
      CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
      cache = cacheFactory.createCache(Collections.emptyMap());
    } catch (CacheException e) {
      log.severe("Exception creating cache: " + e.getMessage() + ": " + e.getStackTrace());
      cache = null;
    }

    // Read important properties from file
    Properties properties = new Properties();
    try {
      properties.load(new FileInputStream("WEB-INF/project.properties"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    CLIENT_ID = properties.getProperty("clientId");
    CLIENT_SECRET = properties.getProperty("clientSecret");
    REDIRECTION_ENDPOINT = properties.getProperty("redirectionEndpoint");
    AUTHORIZATION_ENDPOINT = properties.getProperty("authorizationEndpoint");
    TOKEN_ENDPOINT = properties.getProperty("tokenEndpoint");

    FACEBOOK_API_ENDPOINT = "https://graph.facebook.com/v2.5/";
    // TODO: Increase to at least 100 (preferably 250, which appears to be a max)
    MAX_NUMBER_OF_FACEBOOK_POSTS_TO_REQUEST = 50;
    // TODO: Curate this list
    FACEBOOK_REQUESTED_PROFILE_FIELDS =
        "id,about,age_range,bio,birthday,context,currency,devices,education,email,favorite_athletes,favorite_teams,first_name,gender,hometown,inspirational_people,install_type,installed,interested_in,is_shared_login,is_verified,languages,last_name,link,location,locale,meeting_for,middle_name,name,name_format,payment_pricepoints,test_group,political,relationship_status,religion,security_settings,significant_other,sports,quotes,third_party_id,timezone,updated_time,shared_login_upgrade_required_by,verified,video_upload_limits,viewer_can_send_gift,website,work,cover";
    FACEBOOK_REQUESTED_FEED_FIELDS = "id,name,type,message,status_type,created_time,from,likes%7Bid,name%7D";
  }
}
