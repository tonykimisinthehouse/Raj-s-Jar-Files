package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.helper.ListViewItemDTO;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels.ShipMarketViewModel;

public class ShipMarketActivity extends AppCompatActivity {

    /**
     * View model for ShipMarket activity
     */
    private ShipMarketViewModel viewModel;

    private ListView selectorGoodsForSale;
    private ListView selectorGoodsOnShip;

    private List<ListViewItemDTO> listGoodsForSale;
    private List<ListViewItemDTO> listGoodsOnShip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_market);

        this.viewModel = ViewModelProviders.of(this).get(ShipMarketViewModel.class);

        this.assignViews();
        this.updateGoods();
    }

    private void assignViews() {
        this.selectorGoodsForSale = findViewById(R.id.goodsForSale);
        this.selectorGoodsOnShip = findViewById(R.id.goodsOnShip);
    }

    private void updateGoods() {
        // Get latest string representations of goods
        try {
            Player player = Model.current.getPlayer();
            String goodsForSale = player.getPlanet().getMarketplace().toString();
            String goodsOnShip = player.getShip().getGoods();

            // Split by newlines to get arrays
            this.listGoodsForSale = new ArrayList<>();
            this.listGoodsOnShip = new ArrayList<>();
            for (String desc : goodsForSale.split("\n")) {
                ListViewItemDTO item = new ListViewItemDTO();
                item.setItemText(desc);
                this.listGoodsForSale.add(item);
            }
            for (String desc : goodsOnShip.split("\n")) {
                ListViewItemDTO item = new ListViewItemDTO();
                item.setItemText(desc);
                this.listGoodsOnShip.add(item);
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void buyGoods(View v) {
        // TODO get list of checked goods
        // TODO tell market to sell
    }

    public void sellGoods(View v) {
        // TODO get list of checked goods
        // TODO tell ship to sell
    }

}
