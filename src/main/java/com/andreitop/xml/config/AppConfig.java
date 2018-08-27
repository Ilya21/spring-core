package com.andreitop.xml.config;


import com.andreitop.xml.mount.Mount;
import com.andreitop.xml.mount.Tiger;
import com.andreitop.xml.mount.Wolf;
import com.andreitop.xml.unit.Human;
import com.andreitop.xml.unit.Orc;
import com.andreitop.xml.unit.Troll;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
@ComponentScan(basePackages = "com.andreitop.xml")
@PropertySource("classpath:config/heroes.properties")
public class AppConfig {

    @Value("${character.created}")
    private String heroes;

    @Bean
    public Map<String,Mount> trollMountMap(){
        HashMap<String, Mount> trollMountMap = new HashMap<>();
        trollMountMap.put("m1", frostWolf());
        trollMountMap.put("m2", shadowTiger());
        return trollMountMap;
    }

    @Bean
    public Set<Mount> trollMountSet(){
        Set<Mount> trollMountSet = new HashSet<>();
        trollMountSet.add(shadowTiger());
        trollMountSet.add(frostWolf());
        return trollMountSet;
    }


    @Bean
    public Wolf frostWolf() {
        return new Wolf();
    }

    @Bean
    public Tiger shadowTiger() {
        return new Tiger();
    }

    @Bean
    public Human knight() {
        return new Human(shadowTiger(), "thunderFury", "soulBlade");
    }

    @Bean
    public Orc trall(){
        Orc trall = new Orc(frostWolf());
        trall.setWeapon("furryAxe");
        trall.setColorCode(9);
        return trall;
    }

    @Bean
    public SimpleDateFormat dateFormatter(){
        return new SimpleDateFormat("dd/mm/yyyy");
    }

    @Bean
    public Troll zulJin() throws ParseException {
        Troll zulJin = new Troll();
        zulJin.setColorCode(ThreadLocalRandom.current().nextInt(1, 10));
        zulJin.setCreationDate(dateFormatter().parse(heroes));
        zulJin.setListOfMounts(Arrays.asList(Troll.DEFAULT_MOUNT, null, shadowTiger()));
        zulJin.setSetOfMounts(trollMountSet());
        zulJin.setMapOfMounts(trollMountMap());
        return zulJin;
    }





























}
