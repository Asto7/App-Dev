package com.project.headlines.clicklisteners;

import com.project.headlines.model.News;

public interface NewsDialogClickListeners {

    void onGotoWebSiteClick(String url);

    void onDismissClick();

    void onShareClick(String title, String url);

    void onSaveClick(News news);
}
