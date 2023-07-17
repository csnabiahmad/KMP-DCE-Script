package model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class LPsAudioModel : ArrayList<LPsAudioModel.LPAudioModelItem>(){
    @Serializable
    data class LPAudioModelItem(
        @SerialName("link")
        var link: String? // https://lp-audios-bucket.s3.ap-south-1.amazonaws.com/Urdu+Literacy/Nanhi+Chirya/Nanhi+chidya1.MOV.mp3
    )
}