package org.telegram.messenger.acestream;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class AceStream {
    public static void open(final Context context, String contentID, boolean skipResolver) {
        if (contentID == null || contentID.length() == 0) {
            return;
        }

        Intent intent = new Intent("org.acestream.action.start_content");
        intent.setData(Uri.parse("acestream:?content_id=" + contentID));

        if (skipResolver) {
            // Tell Ace Stream app to use its internal player for playback
            // Without this option Ace Stream app can show resolver (list of players to allow user
            // to select where to start playback).
            intent.putExtra("org.acestream.EXTRA_SELECTED_PLAYER", "{\"type\": 3}");
        }

        Intent chooser = Intent.createChooser(intent, "Select player");
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(chooser);
        } else {
            Toast.makeText(context, "No player found", Toast.LENGTH_SHORT).show();
        }
    }
}
