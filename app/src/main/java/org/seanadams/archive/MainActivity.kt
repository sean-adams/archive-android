package org.seanadams.archive

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.seanadams.archive.ui.theme.ArchiveTheme

class MainActivity : ComponentActivity() {
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent?.action == Intent.ACTION_SEND) {
            if ("text/plain" == intent.type) {
                val data: String? = intent?.getStringExtra(Intent.EXTRA_TEXT)
                openWebPage(data, this@MainActivity) // Handle text being sent
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            ArchiveTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    About()
                }
            }
        }
    }
}

fun openWebPage(uri: String?, context: Context) {
    val webpage: Uri = Uri.parse("http://archive.is/newest/${uri}")
    val webIntent = Intent(Intent.ACTION_VIEW, webpage)
    context.startActivity(webIntent)
}

@Composable
fun About() {
    Text(text = "A simple app to allow easily sharing any link to archive.is.\n\nTo use this app, select the \"Archive\" target from the share sheet when sharing a link, and the latest snapshot will open in your default browser.")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArchiveTheme {
        About()
    }
}