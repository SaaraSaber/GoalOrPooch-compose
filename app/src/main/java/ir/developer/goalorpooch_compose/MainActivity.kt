package ir.developer.goalorpooch_compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("RestrictedApi")
class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        window.statusBarColor = ContextCompat.getColor(this, R.color.red)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.red)

        sharedViewModel.addCard(
            id = 0,
            name = "سنگ مفت گنجشک مفت",
            description = "این کارت به تیم حدس زننده کمک میکند که اگر یکبار اشتباه کند و گل را درست حدس نزند یک دست دیگر را که به آن مشکوک است باز کند.از این کارت برای هر تیم دو عدد وجود دارد."
        )
        sharedViewModel.addCard(
            id = 1,
            name = "گوی بزرگ",
            description = "این کارت به تیم حدس زننده کمک می\u200Cکند تا به تیم مقابل دستور دهد تا به جای بازی با گوی معمولی با گوی بزرگتر بازی کند. در واقع بازی با گوی بزرگتر سخت\u200Cتر می\u200Cشود و گل زودتر لو می\u200Cرود."
        )
        sharedViewModel.addCard(
            id = 2,
            name = "گوی صدا دار",
            description = " کارت گوی صدا دار یکی از هیجان\u200Cانگیزترین کارت\u200Cهای بازی است. چون به تیم حدس زننده کمک می\u200Cکند تا گل را از تیم مقابل راحت\u200Cتر پس بگیرد چرا که تیم مقابل مجبور از با گویی بازی کند که با هر حرکت صدا می\u200Cدهد!"
        )
        sharedViewModel.addCard(
            id = 3,
            name = "یک تیر و دو نشان",
            description = "با پوچ کردن درست یک دست سر گروه تیم مقابل موظف است یک دست دیگر را پوچ کند.از این کارت دو عدد برای هر تیم وجود دارد."
        )
        sharedViewModel.addCard(
            id = 4,
            name = "آنتن",
            description = "تیم حدس زننده می تواند از تیم حریف سوالی بپرسد بازیکن موظف است حقیقت را با بله و خیر جواب دهد."
        )
        sharedViewModel.addCard(
            id = 5,
            name = "دوئل",
            description = "بازیکنی که صاحب گل است گل را نشان داده و به صورت تک به تک با کسی وارد نبرد می شود."
        )
        sharedViewModel.addCard(
            id = 6,
            name = "خالی بازی",
            description = "تیم حریف باید سه خالی بازی مجدد انجام دهد."
        )
        sharedViewModel.addCard(
            id = 7,
            name = "حذف یک دست",
            description = "سر گروه تیم حریف باید یکی از دستان تیم خود را پوچ کند."
        )

        setContent {
            Navigation(sharedViewModel = sharedViewModel)
        }
    }
}

