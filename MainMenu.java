package com.appsilo.ghalibgazals;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainMenu
  extends Activity
{
  public static String INTERSTITIAL_2 = "ca-app-pub-2329300861956934/6490570604";
  private Runnable Hide_Load = new Runnable()
  {
    public void run()
    {
      MainMenu.access$108(MainMenu.this);
      if (MainMenu.this.done == 2)
      {
        RelativeLayout localRelativeLayout = MainMenu.this.rlLoading;
        localRelativeLayout.setVisibility(8);
      }
    }
  };
  public AdView adView;
  public Button btnShare;
  public int c1 = 0;
  public int c2 = 0;
  private int done = 0;
  public int firstThree = 0;
  private InterstitialAd interstitial2;
  private Timer myTimer;
  public Date previousTime = new Date();
  public RelativeLayout rlLoading;
  public WebView wvMainMenu;
  
  private void TimerMethod()
  {
    runOnUiThread(this.Hide_Load);
  }
  
  public static int dateDiff(Date paramDate, char paramChar)
  {
    Date localDate = new Date();
    long l = 0L;
    switch (paramChar)
    {
    }
    for (;;)
    {
      return (int)l;
      l = (localDate.getTime() - paramDate.getTime()) / 1000L;
      continue;
      l = (localDate.getTime() - paramDate.getTime()) / 60000L;
      continue;
      l = (localDate.getTime() - paramDate.getTime()) / 3600000L;
      continue;
      l = (localDate.getTime() - paramDate.getTime()) / 86400000L;
    }
  }
  
  private void loadAdvt()
  {
    this.adView = new AdView(this);
    this.adView.setAdSize(AdSize.BANNER);
    this.adView.setAdUnitId("ca-app-pub-2329300861956934/8604964605");
    ((LinearLayout)findViewById(2131165190)).addView(this.adView);
    AdRequest localAdRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("8992D1FF7014BE159B28C60C98FC1D25").build();
    this.adView.loadAd(localAdRequest);
  }
  
  public void autoResize()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    int i = localDisplayMetrics.heightPixels;
    ViewGroup.LayoutParams localLayoutParams = this.wvMainMenu.getLayoutParams();
    localLayoutParams.height = (i - 50);
    this.wvMainMenu.setLayoutParams(localLayoutParams);
  }
  
  public void btnShare_Click()
  {
    this.btnShare.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent();
        localIntent.setAction("android.intent.action.SEND");
        localIntent.putExtra("android.intent.extra.TEXT", "I like reading Mirza Ghalib Ghazals : https://play.google.com/store/apps/details?id=com.appsilo.ghalibgazals ");
        localIntent.setType("text/plain");
        MainMenu.this.startActivity(localIntent);
      }
    });
  }
  
  public void displayInterstitial2()
  {
    AdRequest localAdRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("8992D1FF7014BE159B28C60C98FC1D25").build();
    this.interstitial2.loadAd(localAdRequest);
    if (this.interstitial2.isLoaded()) {
      this.interstitial2.show();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903040);
    this.btnShare = ((Button)findViewById(2131165191));
    this.rlLoading = ((RelativeLayout)findViewById(2131165192));
    getIntent();
    this.wvMainMenu = ((WebView)findViewById(2131165189));
    this.wvMainMenu.getSettings().setJavaScriptEnabled(true);
    this.wvMainMenu.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    this.wvMainMenu.addJavascriptInterface(new WebappInterface(this), "Android");
    this.wvMainMenu.loadUrl("file:///android_asset/index.html");
    this.interstitial2 = new InterstitialAd(this);
    this.interstitial2.setAdUnitId(INTERSTITIAL_2);
    this.interstitial2.setAdListener(new AdListener()
    {
      public void onAdLoaded()
      {
        MainMenu.this.displayInterstitial2();
      }
    });
    this.wvMainMenu.setWebViewClient(new WebViewClient()
    {
      public void onLoadResource(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if ((paramAnonymousString.contains("2.txt.2")) || (paramAnonymousString.contains("1.txt.1")))
        {
          if (MainMenu.dateDiff(MainMenu.this.previousTime, 'S') >= 120)
          {
            MainMenu.this.displayInterstitial2();
            MainMenu.this.previousTime = new Date();
          }
          if ((MainMenu.this.c1 > 3) && (MainMenu.this.firstThree == 0))
          {
            MainMenu.this.displayInterstitial2();
            MainMenu.this.c1 = 0;
            MainMenu.this.firstThree = 1;
          }
        }
        else
        {
          return;
        }
        MainMenu localMainMenu = MainMenu.this;
        localMainMenu.c1 = (1 + localMainMenu.c1);
      }
    });
    loadAdvt();
    btnShare_Click();
    this.myTimer = new Timer();
    this.myTimer.schedule(new TimerTask()
    {
      public void run()
      {
        MainMenu.this.TimerMethod();
      }
    }, 0L, 3000L);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      new AlertDialog.Builder(this).setIcon(17301543).setTitle("Mirza Ghalib Ghazals").setMessage("Please rate this application and give your feedback. Your feedback is important to us.").setPositiveButton("Exit", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          MainMenu.this.finish();
        }
      }).setNegativeButton("Cancel", null).setNeutralButton("     Rate Now!     ", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          MainMenu.this.rateApplication();
          MainMenu.this.finish();
        }
      }).show();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public void rateApplication()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("market://details?id=com.appsilo.ghalibgazals"));
    startActivity(localIntent);
  }
}


/* Location:           E:\apk tutorial\classes-dex2jar.jar
 * Qualified Name:     com.appsilo.ghalibgazals.MainMenu
 * JD-Core Version:    0.7.0.1
 */