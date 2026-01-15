import torch

DEVICE = "cuda" if torch.cuda.is_available() else "cpu"

CAPTION_MODEL_NAME = "Salesforce/blip-image-captioning-large"
EMBED_MODEL_NAME = "all-MiniLM-L6-v2"

MAX_TOKENS = 50