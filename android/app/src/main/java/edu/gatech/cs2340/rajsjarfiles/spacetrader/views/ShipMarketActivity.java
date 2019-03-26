package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Item;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.helper.ListViewItemCheckboxBaseAdapter;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.helper.ListViewItemDTO;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels.ShipMarketViewModel;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;

public class ShipMarketActivity extends BaseActivity {

    /**
     * View model for ShipMarket activity
     */
    private ShipMarketViewModel viewModel;

    private TextView viewCreditCount;
    private ListView viewGoodsForSale;
    private ListView viewGoodsOnShip;
    private TextView viewCargoCapacity;
    private TextView viewPlanetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_market);

        this.viewModel = ViewModelProviders.of(this).get(ShipMarketViewModel.class);

        this.assignViews();
        this.updateGoods();
        this.alertDialog();
    }

    private void assignViews() {
        this.viewCreditCount = findViewById(R.id.view_credit_count);
        this.viewGoodsForSale = findViewById(R.id.selector_goods_for_sale);
        this.viewGoodsOnShip = findViewById(R.id.selector_goods_on_ship);
        this.viewGoodsForSale.setOnItemClickListener(listener);
        this.viewGoodsOnShip.setOnItemClickListener(listener);
        this.viewCargoCapacity = findViewById(R.id.view_cargo_capacity);
        this.viewPlanetName = findViewById(R.id.view_planet);
    }

    private final AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Get user-selected item
            final ListViewItemDTO item = (ListViewItemDTO) parent.getAdapter().getItem(position);

            // Get checkbox state
            final CheckBox box = view.findViewById(R.id.list_view_item_checkbox);
            final boolean checked = box.isChecked();

            // Toggle checkbox state to on
            item.setChecked(true);
            box.setChecked(true);

            // Determine whether we should buy or sell, based on the parent view
            final boolean buyNotSell = parent == ShipMarketActivity.this.viewGoodsForSale;

            // Display a toast to tell user
            String operationText = buyNotSell ? "Buying " : "Selling ";
            String toastText = operationText + item.getItemText() + "...";
            Toast.makeText(ShipMarketActivity.this, toastText, Toast.LENGTH_SHORT).show();

            // Delay before running operation
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Toggle checkbox state back off
                    item.setChecked(false);
                    box.setChecked(false);

                    // Buy
                    if (buyNotSell) {
                        boolean result = Model.current.getPlayer().makePurchase(item.getGood().getGood(), 1);
                    }

                    // Sell
                    else {
                        boolean result = Model.current.getPlayer().makeSales(item.getGood().getGood(), 1);
                    }

                    // Reload goods
                    ShipMarketActivity.this.updateGoods();
                }
            }, 1000);
        }
    };

    private void updateGoods() {
        // Get latest string representations of goods
        try {
            Player player = Model.current.getPlayer();
            int credits = player.getCredits();
            int totalCargoCapacity = player.getShip().getCargoCapacity();
            int availableCargoCapacity = player.getShip().getAvailableCargoCapacity();
            Collection<Item> goodsForSale = player.getPlanet().getMarketplace().getItems();
            Collection<Item> goodsOnShip = player.getShip().getCargoGoods();

            // Split by newlines to get arrays
            List<ListViewItemDTO> listGoodsForSale = new ArrayList<>();
            List<ListViewItemDTO> listGoodsOnShip = new ArrayList<>();

            for (Item item : goodsForSale) {
                // Only add with a positive quantity of item
                if (item.getQuantity() > 0) {
                    ListViewItemDTO view = new ListViewItemDTO();
                    view.setGood(item);
                    listGoodsForSale.add(view);
                }
            }
            for (Item item : goodsOnShip) {
                // Only add with a positive quantity of item
                if (item.getQuantity() > 0) {
                    ListViewItemDTO view = new ListViewItemDTO();
                    view.setGood(item);
                    listGoodsOnShip.add(view);
                }
            }

            final ListViewItemCheckboxBaseAdapter adapterGoodsForSale = new
                    ListViewItemCheckboxBaseAdapter(this, listGoodsForSale);
            final ListViewItemCheckboxBaseAdapter adapterGoodsOnShip = new
                    ListViewItemCheckboxBaseAdapter(this, listGoodsOnShip);

            adapterGoodsForSale.notifyDataSetChanged();
            adapterGoodsOnShip.notifyDataSetChanged();

            // Set data on views
            this.viewCreditCount.setText("Credits: " + credits);
            this.viewGoodsForSale.setAdapter(adapterGoodsForSale);
            this.viewGoodsOnShip.setAdapter(adapterGoodsOnShip);

            this.viewCargoCapacity.setText("Capacity: " + (totalCargoCapacity - availableCargoCapacity) + "/" + totalCargoCapacity);
            this.viewPlanetName.setText(Model.current.getPlayer().getPlanet().getName() + " Marketplace");
        }
        catch (NullPointerException e) {
            Log.e("RAJ", e.getMessage());
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

    private void alertDialog() {
        // Show dialog alerting price increase
        StringBuilder stringBuilder = new StringBuilder();

        Player player = Model.current.getPlayer();
        Collection<Item> goodsForSale = player.getPlanet().getMarketplace().getItems();
        for (Item item : goodsForSale) {
            if (item.getGood().getIE() == player.getPlanet().getMarketplace().getEvent()) {
                int percentageIncrease = (item.getPrice() - item.getGood().getBasePrice()) / item.getGood().getBasePrice() * 100;
                stringBuilder.append("- " + item.getGood().getName()+ " is in shortage. ("+ (percentageIncrease)+"% price increase)\n");
            }
        }

        // Show dialog only when there is an surging item
        if (stringBuilder.length() != 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("DUE TO " + player.getPlanet().getMarketplace().getEvent().toString() + ", FOLLOWING ITEMS ARE UNDER PRICE SURGE!");
            builder.setMessage(stringBuilder.toString());
            builder.setCancelable(true);
            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Good Luck!", Toast.LENGTH_SHORT).show();
                }
            });
            final AlertDialog dialog = builder.create();
            dialog.setOnShowListener( new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface arg0) {
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
                }
            });
            dialog.show();
        }
    }
}
