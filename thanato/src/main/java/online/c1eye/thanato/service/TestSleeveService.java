package online.c1eye.thanato.service;


import online.c1eye.thanato.mapper.BannerMapper;
import online.c1eye.thanato.model.BannerDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestSleeveService {

    @Autowired
    private BannerMapper bannerMapper;

    public List<BannerDO> getBanners() {
        return this.bannerMapper.getAllBanners();
    }

    public List<BannerDO> getBanners1() {
        return this.bannerMapper.getAllBanners1();
    }

    public long insertBanner() {
        BannerDO bannerDO = new BannerDO();
        bannerDO.setName("NewBanner");
        bannerDO.setTitle("NewBannerTitle");

        bannerMapper.insertBanner(bannerDO);
        return bannerDO.getId();
    }
}
