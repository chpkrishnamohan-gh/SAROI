from transformers import BlipProcessor, BlipForConditionalGeneration
import torch
from app.config import DEVICE, CAPTION_MODEL_NAME, MAX_TOKENS

class CaptionModel:
    def __init__(self):
        self.processor = BlipProcessor.from_pretrained(
            CAPTION_MODEL_NAME,
            local_files_only=True
        )
        self.model = BlipForConditionalGeneration.from_pretrained(
            CAPTION_MODEL_NAME,
            local_files_only=True
        ).to(DEVICE)

    def generate(self, image):
        inputs = self.processor(image, return_tensors="pt").to(DEVICE)

        with torch.no_grad():
            output = self.model.generate(
                **inputs,
                max_new_tokens=MAX_TOKENS
            )

        return self.processor.decode(
            output[0],
            skip_special_tokens=True
        )

caption_model = CaptionModel()
