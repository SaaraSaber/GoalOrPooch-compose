package ir.developer.goalorpooch_compose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.developer.goalorpooch_compose.database.repository.CardRepository
import ir.developer.goalorpooch_compose.model.CardModel
import ir.developer.goalorpooch_compose.model.GameGuideModel
import ir.developer.goalorpooch_compose.model.PlayerModel
import ir.developer.goalorpooch_compose.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: CardRepository
) : ViewModel() {

    private val _allTasks =
        MutableStateFlow<List<CardModel>>(emptyList())
    val allCards: StateFlow<List<CardModel>> = _allTasks

    fun getAllCards() {
        viewModelScope.launch {
            repository.getCards.collect {
                _allTasks.value = it
            }
        }
    }

    fun addCard(id: Int, name: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val cardModel = CardModel(
                id = id,
                name = name,
                description = description,
                isSelect = false,
                disable = false
            )
            repository.addCard(cardModel = cardModel)
        }
    }

    fun updateCard(
        id: Int,
        name: String,
        description: String,
        isSelect: Boolean,
        disable: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val cardModel = CardModel(
                id = id,
                name = name,
                description = description,
                isSelect = isSelect,
                disable = disable
            )
            repository.updateCard(cardModel)
        }
    }

    fun randomCardsTeam1(): List<CardModel> {
        val randomCards = allCards.value.shuffled().take(Utils.THE_NUMBER_OF_PLAYING_CARDS)
        PlayerModel(
            id = 0,
            hasCard = true,
            numberKhaliBazy = 3,
            numberMokaab = 2,
            hasGoal = false,
            cards = randomCards
        )
        return randomCards
    }

    fun randomCardsTeam2(): List<CardModel> {
        val randomCards = allCards.value.shuffled().take(Utils.THE_NUMBER_OF_PLAYING_CARDS)
        PlayerModel(
            id = 1,
            hasCard = true,
            numberKhaliBazy = 3,
            numberMokaab = 2,
            hasGoal = false,
            cards = randomCards
        )
        return randomCards
    }

    val hasCardTeam1 = false
    val hasCardTeam2 = false

    fun getItemsGameGuide(): List<GameGuideModel> {
        return listOf(
            GameGuideModel(
                title = "نحوه بازی",
                description = "مسابقه گل یا پوچ یک بازی گروهی است که دو تیم نفس\u200Cگیر به مصاف هم می\u200Cروند. در هر دوره بازی گل یا پوچ دو تیم ۳ نفره روبروی هم قرار می\u200Cگیرند و بازی گل یا پوچ را با قوانین مخصوص این مسابقه انجام می\u200Cدهند.\n" +
                        "قوانین بازی هم ساده و راحت است. هر بار که گل دست یک تیم باشد تیم مقابل باید با سه خالی بازی گل را حدس بزند. اگر گل را حدس بزند می\u200Cتواند بازی کند و اگر حدس نزند یک امتیاز به تیمی داده می\u200Cشود که گل را حفظ کرده است. اما اگر تیم مقابل که می\u200Cخواهد حدس بزند یک ضرب گل را بگیرد و هیچ دستی را پوچ نکند ۲ امتیاز می\u200Cگیرد"
            ),
            GameGuideModel(
                title = "کارت\u200Cهای بازی گل یا پوچ",
                description = "در هر دست بازی علاوه بر اینکه هر گروه اجازه دارد سه بار دستور خالی بازی بدهد می\u200Cتواند از یک کارت هم استفاده کند. کارت\u200Cهایی حاوی برخی دستورها که به تیم حدس زننده کمک می\u200Cکند تا راحت\u200Cتر گل را پیدا کند؛\n" +
                        "کارت سنگ مفت گنجشک مفت این کارت به تیم حدس زننده کمک می\u200Cکند که اگر یکبار اشتباه کند و گل را درست حدس نزند یک دست دیگر را که به آن مشکوک است باز کند.از این کارت برای هر تیم دو عدد وجود دارد.\n" +
                        "کارت گوی بزرگ این کارت به تیم حدس زننده کمک می\u200Cکند تا به تیم مقابل دستور دهد تا به جای بازی با گوی معمولی با گوی بزرگتر بازی کند. در واقع بازی با گوی بزرگتر سخت\u200Cتر می\u200Cشود و گل زودتر لو می\u200Cرود.\n" +
                        "کارت گوی صدا دار کارت گوی صدا دار یکی از هیجان\u200Cانگیزترین کارت\u200Cهای بازی است. چون به تیم حدس زننده کمک می\u200Cکند تا گل را از تیم مقابل راحت\u200Cتر پس بگیرد چرا که تیم مقابل مجبور از با گویی بازی کند که با هر حرکت صدا می\u200Cدهد!\n" +
                        "کارت یک تیر و دو نشان با پوچ کردن درست یک دست سر گروه تیم مقابل موظف است یک دست دیگر را پوچ کند.از این کارت دو عدد برای هر تیم وجود دارد.\n" +
                        "کارت آنتن تیم حدس زننده می تواند از تیم حریف سوالی بپرسد بازیکن موظف است حقیقت را با بله و خیر جواب دهد.\n" +
                        "کارت دوئل بازیکنی که صاحب گل است گل را نشان داده و به صورت تک به تک با کسی وارد نبرد می شود.\n" +
                        "کارت خالی بازی تیم حریف باید سه خالی بازی مجدد انجام دهد.\n" +
                        "کارت حذف یک دست سر گروه تیم حریف باید یکی از دستان تیم خود را پوچ کند."
            ),
            GameGuideModel(
                title = "چینش کارت های بازی گل یا پوچ",
                description = "هر تیم 9 کارت مقابل خود دارد که باید 5 تا از این کارت ها را به صورت شانسی انتخاب کند ولی توجه کنید :\n" +
                        "دو کارت سمت راست یکی دوئل هست یکی آنتن که یکی را شانسی انتخاب می کنید.\n" +
                        "دو کارت سمت چپ یکی گوی بزرگ هست یکی گوی صدا دار که یکی را شانسی انتخاب می کنید.\n" +
                        "از پنج کارت وسط دو تا سنگ مفت گنجشک مفت وجود دارد یکی سه خالی بازی یکی حذف یک دست و دیگری یک تیر و دو نشان که سه تا را شانسی انتخاب می کنیم."
            ),
            GameGuideModel(
                title = "شاه گل بازی گل یا پوچ",
                description = "بازی در مجموع 9 امتیازی می باشد و تیمی که به امتیاز 8 برسد امتیاز آخر اسمش شاه گل است و اگر بتواند شاه گل را درست حدس بزند تیم بازنده 3 امتیاز از دست می دهد.\n" +
                        "در شاه گل 6 خالی باز کن داریم و حق پوچ کردن دستی رو نداریم.\n" +
                        "اگر برای بار دوم شاه گل را حریف بگیرد دیگه امتیاز معنی ندارد و فقط با دوئل بازی پیش می رود.هر سه بازیکن رو در رو هر کدام یکبار دوئل می کنند و در مجموع کسی که کمتر امتیاز کسب کند باخته است و اگر نتیجه مساوی شد به تک گل می رویم.\n" +
                        "اگر یکبار شما و یکبار تیم حریف به شاه گل رسید سپس برای بار دوم تیمی که به شاه گل برسد می رویم برای دوئل.\n" +
                        "در مرحله فینال که 21 امتیازی هست اگر شاه گل را بگیرید تیم حریف 5 امتیاز پایین می آید.\n" +
                        "در شاه گل اگر رکب بخورید یا نا خودآگاه دست خودتون رو پوچ کنید انگار حریف گل را گرفته است."
            ),
            GameGuideModel(
                title = "روش های امتیاز دهی بازی گل یا پوچ",
                description = "در مرحله مقدماتی بازی ها 9 امتیازی هست و هر گروه 3 تیم دارد که 2 تیم صعود می کنند.\n" +
                        "اگر تیمی دو برد داشته باشد به عنوان تیم اول صعود می کند و تیمی که یک برد دارد تیم دوم.اگر هر تیم یک برد داشته باشد امتیاز شماری می کنیم.\n" +
                        "تیم اول گروه اول با تیم دوم گروه دوم در مرحله نیمه نهایی بازی می کنند و تیم دوم با تیم اول آن گروه.\n" +
                        "در مرحله نیمه نهایی بازی ها 11 امتیازی هست و شاه گل امتیاز 10 می باشد.هر دو تیم رفت و برگشتی باهم بازی می کنند.\n" +
                        "اگر هر دو تیم یک برد کسب کنند به دوئل می رویم و دوئل تک به تک نتیجه را مشخص می کند و امتیاز شماری نداریم.\n" +
                        "مرحله فینال 21 امتیازی هست و هر تیمی به امتیاز 21 برسد برنده بازی است."
            ),
            GameGuideModel(
                title = "مکعب امتیاز بازی گل یا پوچ",
                description = "هر تیم می تواند امتیاز آن دست را به دو با بیشتر افزایش دهد.تیم صاحب گل اگر مکعب رو بپذیره بازی ادامه پیدا می کنه و اگر گل رو بگیرن دو امتیاز بالا می روند.اما اگر نپذیره تیم گل گیر بدون کسب امتیاز صاحب گل می شود.اگر گل را نگیرد تیم دارنده گل به عدد تاس امتیاز بالا می رود.\n" +
                        "در مرحله نیمه نهایی هر تیم فقط یک بار می تواند از امتیاز دو استفاده کند.\n" +
                        "در مرحله فینال می توان از امتیاز ها 2 و 4 و 6 مکعب استفاده کرد.یا حتی اگر شما مکعب 2 بزارید تیم حریف می تواند آن را افزایش دهد.\n" +
                        "از مکعب زمانی می شود استفاده کرد که حداقل سه دست مانده باشد.در یک دور از بازی نمی توان هم از کارت استفاده کرد و هم از مکعب."
            ),
            GameGuideModel(
                title = "نکات مهم بازی گل یا پوچ",
                description = "با شیر یا خط صاحب گل اول انتخاب می شود.سپس لیدر تیم به تنهایی بازی می کند و یک نفر از تیم حریف حدس می زند.اگر درست باشد گل را می گیرد.\n" +
                        "اگر دستی رو پوچ کرده باشید و گل رو درست حدس بزنید فقط گل رو میگیرید و امتیازی دریافت نمی کنید\n" +
                        "اگر گل رو نتونید درست حدس بزنید تیم حریف یک امتیاز دریافت می کنه.\n" +
                        "فقط و فقط اگر یک ضرب بدون پوچ کردن دستی گل رو بگیرید دو امتیاز کسب می کنید.\n" +
                        "هر تیم از 9 کارت بازی 5 کارت را شانسی انتخاب می کند.از دوئل و آنتن یک کارت.از گوی بزرگ و گوی صدا دار یک دست.و از بقیه سه کارت.\n" +
                        "در مرحله فینال از بین 5 کارت وسط 4 کارت می توانید انتخاب کنید.\n" +
                        "در هر نوبت بازی اگر حداقل سه دست باشد فقط از یک کارت می توان استفاده کرد.\n" +
                        "شرط استفاده از کارت باقی ماندن حداقل سه دست از شش دست تیم حریف است.\n" +
                        "اگر گل قبل از شروع بازی از دست بیافتد دوئل انجام می شود.\n" +
                        "اگر گل وسط بازی بازی از دست بیافتد گل دست حریف میافتد و حریف امتیاز نمی گیرد.\n" +
                        "اگر کارت آنتن رو بپرسیم و آن شخص بگوید گل ندارد سپس بدون پوچ بگیریم دو امتیاز ندارد و انگار دو دست پوچ کرده ایم."
            )
        )
    }

}