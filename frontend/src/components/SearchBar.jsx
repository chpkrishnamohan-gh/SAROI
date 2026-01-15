export default function SearchBar({ value, onChange, onSearch }) {
  return (
    <div style={{ display: "flex", gap: 14 }}>
      <input
        placeholder="Search images (e.g. kids playing, sunset, dog)"
        value={value}
        onChange={(e) => onChange(e.target.value)}
      />
      <button onClick={onSearch}>Search</button>
    </div>
  );
}
