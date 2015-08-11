package com.beariksonstudios.existence.gameobjects.settlement.types;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.beariksonstudios.existence.gameobjects.settlement.SettlementType;
import com.beariksonstudios.existence.resources.Resource;
import com.beariksonstudios.existence.resources.map.MapResource;
import com.beariksonstudios.existence.scenes.game.Assets;

import java.util.ArrayList;


/**
 * Created by Neal on 7/17/2015.
 */
public class City implements SettlementType {
    private Texture diamond = Assets.manager.get("settlements/Diamond.png", Texture.class);
    private Image settlementImage;
    public static int MIN_SIZE = 30;
    public static int MAX_SIZE = 120;
    public static ArrayList<Resource> allowedResources = new ArrayList<Resource>();
    private ArrayList<Resource> filteredResource = new ArrayList<Resource>();

    public City(Image image) {
        allowedResources.add(Resource.WATER);
        allowedResources.add(Resource.GRAIN);
        allowedResources.add(Resource.STONE);
        allowedResources.add(Resource.WOOD);
        allowedResources.add(Resource.FRUIT);
        allowedResources.add(Resource.FISH);
        allowedResources.add(Resource.IRON);
        allowedResources.add(Resource.VENISON);
        allowedResources.add(Resource.CRAB);
        allowedResources.add(Resource.GOLD);
        allowedResources.add(Resource.PAPER);
        allowedResources.add(Resource.HIGH_FRUCTOSE_CORN_SYRUP);
        settlementImage = image;
        settlementImage.setDrawable(new TextureRegionDrawable(new TextureRegion(diamond)));
    }

    @Override
    public String getName() {
        return "City";
    }

    @Override
    public void update(double population) {
        float size = ((float)population/10000) + 20;
        if (size < MIN_SIZE)
            size = MIN_SIZE;
        else if (size > MAX_SIZE)
             size = MAX_SIZE;
        settlementImage.setSize(size, size);
    }
    @Override
    public String getResources() {
        String theResource = "";
        for (Resource resource : filteredResource) {
            theResource += resource.name().toLowerCase();
            theResource += " ";
        }
        return theResource;

    }

    @Override
    public ArrayList<Resource> filterResources(ArrayList<MapResource> resources) {
        for (MapResource mapResource : resources) {
            for (Resource resource : mapResource.getSpecificResources()) {
                for (Resource allowedResource : allowedResources) {
                    if(allowedResource == resource){
                        filteredResource.add(resource);
                    }
                }
            }
        }


        return filteredResource;
    }

    @Override
    public float getGrowthRate() {
        return 0.012f;
    }

    @Override
    public Texture getTexture() {
        return diamond;
    }
}
