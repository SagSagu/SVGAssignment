package sagar.svgassignment.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sagar.svgassignment.displayDogs.DisplayDogsActivity
import sagar.svgassignment.generateDogs.GenerateDogsActivity
import sagar.svgassignment.ui.theme.ButtonColor
import sagar.svgassignment.ui.theme.SVGAssignmentTheme
import sagar.svgassignment.utils.LocalCache

class HomeActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        LocalCache.setupPreferences(this)
        setContent {
            SVGAssignmentTheme {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center
                ){
                    HeaderText()
                    CenteredButtons()
                }
            }
        }
    }
}

@Composable
fun HeaderContent(title: String) {
    val context = LocalContext.current
    Box(Modifier.fillMaxWidth()
        .padding(top = 50.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)) {
        Text(
            text = "Back",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = ButtonColor,
            modifier = Modifier.clickable {
                (context as? android.app.Activity)?.finish()
            }
        )
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun HeaderText() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 150.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Random Dog Generator!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CenteredButtons() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MyButton("Generate Dogs!", true)
        MyButton("My Recently Generated Dogs!", false)
    }
}

@Composable
fun MyButton(text: String, generateDogs: Boolean) {
    val context = LocalContext.current
    Button(
        onClick = {
            if (generateDogs){
                val intent = Intent(context, GenerateDogsActivity::class.java)
                context.startActivity(intent)
            } else {
                val intent = Intent(context, DisplayDogsActivity::class.java)
                context.startActivity(intent)
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = ButtonColor
        ),
        modifier = Modifier
            .padding(10.dp),
    ) {
        Text(
            text = text,
            fontSize = 16.sp)
    }
}