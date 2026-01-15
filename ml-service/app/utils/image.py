from PIL import Image
import io

def load_image(file_bytes: bytes):
    return Image.open(io.BytesIO(file_bytes)).convert("RGB")
