from sentence_transformers import SentenceTransformer
from app.config import EMBED_MODEL_NAME

class EmbedModel:
    def __init__(self):
        self.model = SentenceTransformer(
            EMBED_MODEL_NAME
        )

    def embed(self, text: str):
        return self.model.encode(text).tolist()

embed_model = EmbedModel()
