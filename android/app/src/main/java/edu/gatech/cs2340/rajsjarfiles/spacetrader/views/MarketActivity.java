package edu.gatech.cs2340.rajsjarfiles.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Good;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Item;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Planet;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.helper.ListViewItemCheckboxBaseAdapter;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.helper.ListViewItemDTO;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.model.Model;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels.MarketViewModel;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;

/**
 * Activity for a planet's market.
 */
public class MarketActivity extends BaseActivity {

    private TextView viewCreditCount;
    private ListView viewGoodsForSale;
    private ListView viewGoodsOnShip;
    private TextView viewCargoCapacity;
    private TextView viewPlanetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        /**
         * View model for ShipMarket activity
         */
        MarketViewModel viewModel = ViewModelProviders.of(this).get(MarketViewModel.class);

        this.assignViews();
        this.alertDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.updateGoods();
    }

    /**
     * Assigns view references based on the ids.
     */
    private void assignViews() {
        this.viewCreditCount = findViewById(R.id.view_credit_count);
        this.viewGoodsForSale = findViewById(R.id.selector_goods_for_sale);
        this.viewGoodsOnShip = findViewById(R.id.selector_goods_on_ship);
        this.viewGoodsForSale.setOnItemClickListener(listener);
        this.viewGoodsOnShip.setOnItemClickListener(listener);
        this.viewCargoCapacity = findViewById(R.id.view_cargo_capacity);
        this.viewPlanetName = findViewById(R.id.view_planet);
    }

    private final AdapterView.OnItemClickListener listener =
            new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(
                AdapterView<?> parent, View view, int position, long id) {
            // Get user-selected item
            final ListViewItemDTO item =
                    (ListViewItemDTO) parent.getAdapter().getItem(position);

            // Get checkbox state
            final CheckBox box =
                    view.findViewById(R.id.list_view_item_checkbox);
            final boolean checked = box.isChecked();

            // Toggle checkbox state to on
            item.setChecked(true);
            box.setChecked(true);

            // Determine whether we should buy or sell, based on the parent view
            final boolean buyNotSell =
                    parent == MarketActivity.this.viewGoodsForSale;

            // Display a toast to tell user
            String operationText = buyNotSell ? "Buying " : "Selling ";
            String toastText = operationText + item.getItemText() + "...";
            Toast.makeText(
                    MarketActivity.this, toastText, Toast.LENGTH_SHORT).show();

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
                        boolean result = Model.getModel().getPlayer().getWallet().
                                makePurchase(item.getGood().getGood(), 1);
                    } else {
                        boolean result = Model.getModel().getPlayer().getWallet().
                                makeSales(item.getGood().getGood(), 1);
                    }

                    // Reload goods
                    MarketActivity.this.updateGoods();
                }
            }, 1000);
        }
    };

    private void updateGoods() {
        // Get latest string representations of goods
        try {
            Model m = Model.getModel();
            Player player = m.getPlayer();
            int credits = player.getCredits();

            Ship ship = player.getShip();
            int totalCargoCapacity = ship.getCargoCapacity();
            int availableCargoCapacity =
                    ship.getAvailableCargoCapacity();
            Planet planet = m.getPlanet();
            Collection<Item> goodsForSale =
                    planet.getItems();
            Collection<Item> goodsOnShip = player.getCargoGoods();

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
            this.viewCargoCapacity.setText("Capacity: "
                    + (totalCargoCapacity - availableCargoCapacity)
                    + "/" + totalCargoCapacity);


            this.viewPlanetName.setText(planet.getName() + " Marketplace");
        } catch (NullPointerException e) {
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

    /**
     * Creates a dialog that alerts the user of an event.
     */
    private void alertDialog() {
        // Show dialog alerting price increase
        StringBuilder stringBuilder = new StringBuilder();

        Model m = Model.getModel();
        Player player = m.getPlayer();
        Planet planet = player.getPlanet();
        Collection<Item> goodsForSale =
                planet.getItems();

        for (Item item : goodsForSale) {
            Good g = item.getGood();
            if (g.getIE()
                    == planet.getEvent()) {
                int percentageIncrease =
                        ((item.getPrice() - g.getBasePrice())
                                / g.getBasePrice()) * 100;
                stringBuilder.append("- " + g.getName()
                        + " is in shortage. ("
                        + (percentageIncrease)
                        + "% price increase)\n");
            }
        }

        // Show dialog only when there is an surging item
        if (stringBuilder.length() != 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("DUE TO "
                    + planet.getEventString()
                    + ", FOLLOWING ITEMS ARE UNDER PRICE SURGE!");
            builder.setMessage(stringBuilder.toString());
            builder.setCancelable(true);
            builder.setNegativeButton("Ok",
                    new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),
                                "Good Luck!", Toast.LENGTH_SHORT).show();
                    }
                }
            );
            final AlertDialog dialog = builder.create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface arg0) {
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).
                            setTextColor(Color.RED);
                }
            });
            dialog.show();
        }
    }
}
