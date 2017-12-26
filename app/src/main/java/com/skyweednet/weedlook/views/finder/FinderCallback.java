package com.skyweednet.weedlook.views.finder;

/**
 * Created by osx on 23-12-17.
 */

public interface FinderCallback {

    void error(String error);
    void success();
    void notFound();
}
