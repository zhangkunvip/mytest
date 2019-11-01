package controller;

import config.GitAutoRefreshConfig;
import config.GitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitController {

    @Autowired
    private GitConfig gitConfig;

    @Autowired
    private GitAutoRefreshConfig gitAutoRefreshConfig;

    @RequestMapping(value = "show")
    public Object show() {
        return gitConfig;
    }

    @RequestMapping(value = "/autoShow")
    public Object autoShow() {
        return gitAutoRefreshConfig;
    }
}